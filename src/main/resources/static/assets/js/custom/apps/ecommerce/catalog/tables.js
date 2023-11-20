"use strict";

let KTAppBoardNotice = function () {
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

let KTAppBoardSipaNews = function () {
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
        renderHTML += '<a onclick="f_board_sipa_news_detail_modal_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3" data-bs-toggle="modal" data-bs-target="#kt_modal_modify_history">상세정보</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_board_sipa_news_modify_init_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3">수정</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_board_sipa_news_remove(' + '\'' + seq + '\'' + ')" class="menu-link px-3">삭제</a>';
        renderHTML += '</div>';
        renderHTML += '</div>';
        return renderHTML;
    }

    // Public methods
    return {
        init: function () {
            table = document.querySelector('#mng_board_sipa_news_table');

            if (!table) {
                return;
            }

            initDatatable();

            /* Data row clear */
            let dataTbl = $('#mng_board_sipa_news_table').DataTable();
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
            f_board_sipa_news_search();
        }
    };
}();

let KTAppBoardEvent = function () {
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
                    'targets': 11,
                    'data': 'actions',
                    'render': function (data, type, row) { return renderActionsCell(data, type, row); }
                },
                { visible: false, targets: [1] }
            ],
            columns: [
                { data: 'rownum' },
                { data: 'seq'},
                { data: 'lang'},
                { data: 'title' },
                { data: 'writer' },
                { data: 'writeDate' },
                { data: 'location' },
                { data: 'startDate' },
                { data: 'endDate' },
                { data: 'initRegiDttm' },
                { data: 'finalRegiDttm' },
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
        renderHTML += '<a onclick="f_board_event_detail_modal_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3" data-bs-toggle="modal" data-bs-target="#kt_modal_modify_history">상세정보</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_board_event_modify_init_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3">수정</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_board_event_remove(' + '\'' + seq + '\'' + ')" class="menu-link px-3">삭제</a>';
        renderHTML += '</div>';
        renderHTML += '</div>';
        return renderHTML;
    }

    // Public methods
    return {
        init: function () {
            table = document.querySelector('#mng_board_event_table');

            if (!table) {
                return;
            }

            initDatatable();

            /* Data row clear */
            let dataTbl = $('#mng_board_event_table').DataTable();
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
            f_board_event_search();
        }
    };
}();

let KTAppBoardFaq = function () {
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
                    'targets': 8,
                    'data': 'actions',
                    'render': function (data, type, row) { return renderActionsCell(data, type, row); }
                },
                { visible: false, targets: [1] }
            ],
            columns: [
                { data: 'rownum' },
                { data: 'seq'},
                { data: 'lang'},
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
        renderHTML += '<a onclick="f_board_faq_detail_modal_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3" data-bs-toggle="modal" data-bs-target="#kt_modal_modify_history">상세정보</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_board_faq_modify_init_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3">수정</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_board_faq_remove(' + '\'' + seq + '\'' + ')" class="menu-link px-3">삭제</a>';
        renderHTML += '</div>';
        renderHTML += '</div>';
        return renderHTML;
    }

    // Public methods
    return {
        init: function () {
            table = document.querySelector('#mng_board_faq_table');

            if (!table) {
                return;
            }

            initDatatable();

            /* Data row clear */
            let dataTbl = $('#mng_board_faq_table').DataTable();
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
            f_board_faq_search();
        }
    };
}();

let KTAppMemberAscdirectors = function () {
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
                    'targets': 8,
                    'data': 'actions',
                    'render': function (data, type, row) { return renderActionsCell(data, type, row); }
                },
                { visible: false, targets: [1] }
            ],
            columns: [
                { data: 'rownum' },
                { data: 'seq'},
                { data: 'lang'},
                { data: 'companyName'},
                { data: 'companyCeo'},
                { data: 'companyBusinessType'},
                { data: 'companyBusinessItem'},
                { data: 'finalRegiDttm' },
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
        renderHTML += '<a onclick="f_member_ascdirectors_detail_modal_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3" data-bs-toggle="modal" data-bs-target="#kt_modal_modify_history">상세정보</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_member_ascdirectors_modify_init_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3">수정</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_member_ascdirectors_remove(' + '\'' + seq + '\'' + ')" class="menu-link px-3">삭제</a>';
        renderHTML += '</div>';
        renderHTML += '</div>';
        return renderHTML;
    }

    // Public methods
    return {
        init: function () {
            table = document.querySelector('#mng_member_ascdirectors_table');

            if (!table) {
                return;
            }

            initDatatable();

            /* Data row clear */
            let dataTbl = $('#mng_member_ascdirectors_table').DataTable();
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
            f_member_ascdirectors_search();
        }
    };
}();

let KTAppMemberAdviser = function () {
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
                { data: 'position'},
                { data: 'name'},
                { data: 'organization'},
                { data: 'depart'},
                { data: 'tel'},
                { data: 'finalRegiDttm' },
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
        renderHTML += '<a onclick="f_member_adviser_detail_modal_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3" data-bs-toggle="modal" data-bs-target="#kt_modal_modify_history">상세정보</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_member_adviser_modify_init_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3">수정</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_member_adviser_remove(' + '\'' + seq + '\'' + ')" class="menu-link px-3">삭제</a>';
        renderHTML += '</div>';
        renderHTML += '</div>';
        return renderHTML;
    }

    // Public methods
    return {
        init: function () {
            table = document.querySelector('#mng_member_adviser_table');

            if (!table) {
                return;
            }

            initDatatable();

            /* Data row clear */
            let dataTbl = $('#mng_member_adviser_table').DataTable();
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
            f_member_adviser_search();
        }
    };
}();

let KTAppMemberConsultation = function () {
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
                { data: 'position'},
                { data: 'name'},
                { data: 'organization'},
                { data: 'depart'},
                { data: 'tel'},
                { data: 'finalRegiDttm' },
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
        renderHTML += '<a onclick="f_member_consultation_detail_modal_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3" data-bs-toggle="modal" data-bs-target="#kt_modal_modify_history">상세정보</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_member_consultation_modify_init_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3">수정</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_member_consultation_remove(' + '\'' + seq + '\'' + ')" class="menu-link px-3">삭제</a>';
        renderHTML += '</div>';
        renderHTML += '</div>';
        return renderHTML;
    }

    // Public methods
    return {
        init: function () {
            table = document.querySelector('#mng_member_consultation_table');

            if (!table) {
                return;
            }

            initDatatable();

            /* Data row clear */
            let dataTbl = $('#mng_member_consultation_table').DataTable();
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
            f_member_consultation_search();
        }
    };
}();

let KTAppMemberCompany = function () {
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
                    'render': function (data, type, row) { return renderGbnCell(data, type, row); }
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
                { data: 'gbn'},
                { data: 'companyName'},
                { data: 'companyCeo'},
                { data: 'companyBusinessType'},
                { data: 'companyBusinessItem'},
                { data: 'finalRegiDttm' },
                { data: 'actions' }
            ]
        });
    }

    function renderGbnCell(data, type, row){
        let gbn = row.gbn;
        let renderHTML = '준회원';
        if(gbn === 'R'){
            renderHTML = '정회원';
        }
        return renderHTML;
    }

    function renderActionsCell(data, type, row){
        //console.log(row.id);
        let seq = row.seq;
        let renderHTML = '<button type="button" onclick="KTMenu.createInstances()" class="btn btn-sm btn-light btn-flex btn-center btn-active-light-primary" data-kt-menu-trigger="click" data-kt-menu-placement="bottom-end">';
        renderHTML += 'Actions';
        renderHTML += '<i class="ki-duotone ki-down fs-5 ms-1"></i></button>';
        renderHTML += '<div id="kt_menu" class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-gray-600 menu-state-bg-light-primary fw-semibold fs-7 w-150px py-4" data-kt-menu="true">';
        renderHTML += '<div id="kt_menu_item" class="menu-item px-3">';
        renderHTML += '<a onclick="f_member_company_detail_modal_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3" data-bs-toggle="modal" data-bs-target="#kt_modal_modify_history">상세정보</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_member_company_modify_init_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3">수정</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_member_company_remove(' + '\'' + seq + '\'' + ')" class="menu-link px-3">삭제</a>';
        renderHTML += '</div>';
        renderHTML += '</div>';
        return renderHTML;
    }

    // Public methods
    return {
        init: function () {
            table = document.querySelector('#mng_member_company_table');

            if (!table) {
                return;
            }

            initDatatable();

            /* Data row clear */
            let dataTbl = $('#mng_member_company_table').DataTable();
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
            f_member_company_search();
        }
    };
}();

let KTAppMemberPartnership = function () {
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
                    'targets': 8,
                    'data': 'actions',
                    'render': function (data, type, row) { return renderActionsCell(data, type, row); }
                },
                { visible: false, targets: [1] }
            ],
            columns: [
                { data: 'rownum' },
                { data: 'seq'},
                { data: 'lang'},
                { data: 'companyName'},
                { data: 'companyCeo'},
                { data: 'companyBusinessType'},
                { data: 'companyBusinessItem'},
                { data: 'finalRegiDttm' },
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
        renderHTML += '<a onclick="f_member_partnership_detail_modal_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3" data-bs-toggle="modal" data-bs-target="#kt_modal_modify_history">상세정보</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_member_partnership_modify_init_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3">수정</a>';
        renderHTML += '</div>';
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_member_partnership_remove(' + '\'' + seq + '\'' + ')" class="menu-link px-3">삭제</a>';
        renderHTML += '</div>';
        renderHTML += '</div>';
        return renderHTML;
    }

    // Public methods
    return {
        init: function () {
            table = document.querySelector('#mng_member_partnership_table');

            if (!table) {
                return;
            }

            initDatatable();

            /* Data row clear */
            let dataTbl = $('#mng_member_partnership_table').DataTable();
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
            f_member_partnership_search();
        }
    };
}();

// On document ready
KTUtil.onDOMContentLoaded(function () {

    // 정보센터>게시판관리
    // 공지사항
    KTAppBoardNotice.init(); // /mng/board/notice.do
    // SIPA-NEWS
    KTAppBoardSipaNews.init(); // /mng/board/sipaNews.do
    // 행사 게시판
    KTAppBoardEvent.init(); // /mng/board/event.do
    // FAQ
    KTAppBoardFaq.init(); // /mng/board/faq.do

    // 회원사>회원사관리
    // 협회이사
    KTAppMemberAscdirectors.init(); // /mng/member/ascdirectors.do
    // 고문위원
    KTAppMemberAdviser.init(); // /mng/member/adviser.do
    // 자문위원
    KTAppMemberConsultation.init(); // /mng/member/consultation.do
    // 회원사
    KTAppMemberCompany.init(); // /mng/member/company.do
    // 협력기관
    KTAppMemberPartnership.init(); //mng/member/partnership.do

});
