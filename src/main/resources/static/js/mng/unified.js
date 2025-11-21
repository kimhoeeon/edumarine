"use strict";

// 데이터테이블 인스턴스
var datatable;

var DTCustomerUnified = function () {
    var initDatatable = function () {
        datatable = $("#kt_datatable_unified").DataTable({
            searchDelay: 500,
            processing: true,
            serverSide: true,
            order: [[4, 'desc']], // 신청일시 기준 내림차순 정렬
            stateSave: true,
            select: {
                style: 'multi',
                selector: 'td:first-child input[type="checkbox"]',
                className: 'row-selected'
            },
            ajax: {
                url: "/mng/customer/unified/selectList.do",
                type: "POST",
                contentType: "application/json",
                dataType: "json",
                data: function (d) {
                    // 검색 조건 생성
                    var searchData = {
                        condition: $("#search_box").val(),
                        searchText: $("#search_text").val(),
                        status: $("#condition_status").val(), // 신청 상태 필터
                        startRow: d.start,
                        endRow: d.length,
                        isPaging: 'Y'
                    };
                    return JSON.stringify(searchData);
                },
                dataSrc: function (res) {
                    return res;
                }
            },
            columns: [
                { data: 'rownum' },       // No
                { data: 'trainName' },    // 교육과정명
                { data: 'memberName' },   // 신청자
                { data: 'memberPhone' },  // 연락처
                { data: 'initRegiDttm' }, // 신청일시
                { data: 'applyStatus' },  // 상태
                { data: null }            // 기능 (상세보기)
            ],
            columnDefs: [
                {
                    targets: 0,
                    className: 'text-center',
                    orderable: false,
                    render: function (data, type, row, meta) {
                        // 서버사이드 페이징 시 번호 계산 (전체 카운트가 필요하지만 간략히 rownum 사용)
                        return data;
                    }
                },
                {
                    targets: 1, // 교육명
                    className: 'text-start',
                    render: function(data, type, row) {
                        // gbn + gbnDepth 등 조합이 필요하면 여기서 처리
                        return data;
                    }
                },
                { targets: [2, 3, 4], className: 'text-center' },
                {
                    targets: 5, // 상태
                    className: 'text-center',
                    render: function (data, type, row) {
                        var statusClass = 'badge-light-primary';
                        if (data === '결제완료') statusClass = 'badge-light-success';
                        else if (data === '취소신청' || data === '취소완료') statusClass = 'badge-light-danger';
                        else if (data === '환불완료') statusClass = 'badge-light-warning';

                        return '<span class="badge ' + statusClass + ' fw-bold px-4 py-3">' + data + '</span>';
                    }
                },
                {
                    targets: -1, // 기능
                    className: 'text-center',
                    orderable: false,
                    render: function (data, type, row) {
                        // 상세 페이지 이동 URL (추후 생성할 unified_detail.jsp 연결)
                        // 예: /mng/customer/unified/detail.do?seq=AU00001
                        return '<a href="/mng/customer/unified/detail.do?seq=' + row.seq + '" class="btn btn-sm btn-light btn-active-light-primary">상세보기</a>';
                    }
                }
            ]
        });
    };

    return {
        init: function () {
            initDatatable();
        }
    };
}();

// 페이지 로드 시 실행
$(document).ready(function () {
    // 테이블 초기화
    DTCustomerUnified.init();

    // 엔터키 검색 이벤트
    $("#search_text").on("keyup", function (key) {
        if (key.keyCode == 13) {
            f_unified_search();
        }
    });
});

// 검색 함수
function f_unified_search() {
    datatable.ajax.reload(); // 데이터테이블 리로드 (AJAX 재호출)
}

// 엑셀 다운로드 함수
function f_excel_download(type) {
    var condition = $("#search_box").val();
    var searchText = $("#search_text").val();
    var status = $("#condition_status").val();

    var url = "/mng/customer/unified/excel/download.do?fileName=통합신청목록&condition=" + condition + "&searchText=" + searchText + "&status=" + status;
    window.location.href = url;
}