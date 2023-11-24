"use strict";

let DTBoardNotice = function () {
    // Shared variables
    let table;
    let datatable;

    // Private functions
    let initDatatable = function () {
        // Init datatable --- more info on datatables: https://datatables.net/manual/
        datatable = $(table).DataTable({
            'info': false,
            'paging' : false,
            'select': false,
            'ordering': true,
            'order': [[0, 'desc']],
            'columnDefs': [
                {
                    'targets': '_all',
                    'className': 'text-center'
                },
                {
                    'targets': 2,
                    'render': function (data) {
                        if (data === 'KO') {
                            return '국문'
                        } else {
                            return '영문'
                        }
                    }
                },
                {
                    'targets': 3,
                    'render': function (data) {
                        if (data === '1') {
                            return 'V'
                        } else {
                            return '-'
                        }
                    }
                },
                {
                    'targets': 9,
                    'data': 'actions',
                    'render': function (data, type, row) { return renderActionsCell(data, type, row); }
                },
                { visible: false, targets: [1] }
            ],
            columns: [
                { data: 'rownum' },
                { data: 'seq'},
                { data: 'lang'},
                { data: 'noticeGbn' },
                { data: 'title' },
                { data: 'writer' },
                { data: 'writeDate' },
                { data: 'finalRegiDttm' },
                { data: 'viewCnt' },
                { data: 'actions' }
            ]
        });
    }

    function renderActionsCell(data, type, row){
        //console.log(row.id);
        let seq = row.seq;
        let renderHTML = '<button type="button" onclick="KTMenu.createInstances()" class="btn btn-sm btn-light btn-flex btn-center btn-active-light-primary" data-kt-menu-trigger="click" data-kt-menu-placement="bottom-end">';
        renderHTML += 'Actions';
        renderHTML += '<i class="ki-duotone ki-down fs-5 ms-1"></i></button>';
        renderHTML += '<div id="kt_menu" class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-gray-600 menu-state-bg-light-primary fw-semibold fs-7 w-150px py-4" data-kt-menu="true">';
        renderHTML += '<div id="kt_menu_item" class="menu-item px-3">';
        renderHTML += '<a onclick="f_board_notice_detail_modal_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3" data-bs-toggle="modal" data-bs-target="#kt_modal_modify_history">상세정보</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_board_notice_modify_init_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3">수정</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_board_notice_remove(' + '\'' + seq + '\'' + ')" class="menu-link px-3">삭제</a>';
        renderHTML += '</div>';
        renderHTML += '</div>';
        return renderHTML;
    }

    // Public methods
    return {
        init: function () {
            table = document.querySelector('#mng_board_notice_table');

            if (!table) {
                return;
            }

            initDatatable();

            /* Data row clear */
            let dataTbl = $('#mng_board_notice_table').DataTable();
            dataTbl.clear();
            dataTbl.draw(false);

            dataTbl.on('order.dt search.dt', function () {
                let i = dataTbl.rows().count();
                dataTbl.cells(null, 0, { search: 'applied', order: 'applied' })
                    .every(function (cell) {
                        this.data(i--);
                    });
            }).draw();

            /* 조회 */
            f_board_notice_search();
        }
    };
}();

let DTBoardPress = function () {
    // Shared variables
    let table;
    let datatable;

    // Private functions
    let initDatatable = function () {
        // Init datatable --- more info on datatables: https://datatables.net/manual/
        datatable = $(table).DataTable({
            'info': false,
            'paging' : false,
            'select': false,
            'ordering': true,
            'order': [[0, 'desc']],
            'columnDefs': [
                {
                    'targets': '_all',
                    'className': 'text-center'
                },
                {
                    'targets': 2,
                    'render': function (data) {
                        if (data === 'KO') {
                            return '국문'
                        } else {
                            return '영문'
                        }
                    }
                },
                {
                    'targets': 3,
                    'render': function (data) {
                        if (data === '1') {
                            return 'V'
                        } else {
                            return '-'
                        }
                    }
                },
                {
                    'targets': 9,
                    'data': 'actions',
                    'render': function (data, type, row) { return renderActionsCell(data, type, row); }
                },
                { visible: false, targets: [1] }
            ],
            columns: [
                { data: 'rownum' },
                { data: 'seq'},
                { data: 'lang'},
                { data: 'noticeGbn' },
                { data: 'title' },
                { data: 'writer' },
                { data: 'writeDate' },
                { data: 'finalRegiDttm' },
                { data: 'viewCnt' },
                { data: 'actions' }
            ]
        });
    }

    function renderActionsCell(data, type, row){
        //console.log(row.id);
        let seq = row.seq;
        let renderHTML = '<button type="button" onclick="KTMenu.createInstances()" class="btn btn-sm btn-light btn-flex btn-center btn-active-light-primary" data-kt-menu-trigger="click" data-kt-menu-placement="bottom-end">';
        renderHTML += 'Actions';
        renderHTML += '<i class="ki-duotone ki-down fs-5 ms-1"></i></button>';
        renderHTML += '<div id="kt_menu" class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-gray-600 menu-state-bg-light-primary fw-semibold fs-7 w-150px py-4" data-kt-menu="true">';
        renderHTML += '<div id="kt_menu_item" class="menu-item px-3">';
        renderHTML += '<a onclick="f_board_press_detail_modal_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3" data-bs-toggle="modal" data-bs-target="#kt_modal_modify_history">상세정보</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_board_press_modify_init_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3">수정</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_board_press_remove(' + '\'' + seq + '\'' + ')" class="menu-link px-3">삭제</a>';
        renderHTML += '</div>';
        renderHTML += '</div>';
        return renderHTML;
    }

    // Public methods
    return {
        init: function () {
            table = document.querySelector('#mng_board_press_table');

            if (!table) {
                return;
            }

            initDatatable();

            /* Data row clear */
            let dataTbl = $('#mng_board_press_table').DataTable();
            dataTbl.clear();
            dataTbl.draw(false);

            dataTbl.on('order.dt search.dt', function () {
                let i = dataTbl.rows().count();
                dataTbl.cells(null, 0, { search: 'applied', order: 'applied' })
                    .every(function (cell) {
                        this.data(i--);
                    });
            }).draw();

            /* 조회 */
            f_board_press_search();
        }
    };
}();

// On document ready
KTUtil.onDOMContentLoaded(function () {

    // 정보센터>게시판관리
    // 공지사항
    DTBoardNotice.init(); // /mng/board/notice.do
    // 보도자료
    DTBoardPress.init(); // /mng/board/press.do

});
