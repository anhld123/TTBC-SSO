/*
 * Nguyễn Phú Vinh (0849.358.358) - 03/03/2021-> cha đẻ
 * Lê Đức Anh sửa sau sát nhập
 * Xử lý báo cáo khi chọn đơn vị
 */
$(document).ready(function () {
    const PRIDPGD = ["PARA_MAPGD", "PV_MAPGD", "PARA_MAPGD_MAPGD", "PV_POS_CD", "PV_POS_CD_MAPGD", "POS_CD", "PARA_POS"];
    const PRIDXA = ["PARA_MAXA", "PV_MAXA", "PV_MAXAD", "PARA_COMMUNEID"];
    const PRIDTHON = ["PARA_MATHON", "PV_MATHON"];
    const PRIDTO = ["PARA_MATO", "PV_MATO"];

    const posTriggers = [...PRIDPGD];
    const xaTriggers = ["PARA_MAXA", "PV_MAXA", "PV_MAXAD"];
    const thonTriggers = PRIDTHON;

    // Đăng ký sự kiện thay đổi cho các phần tử POS
    posTriggers.forEach(id => {
        $("#" + id).change(() => {
            updateAll(PRIDPGD, PRIDXA, PRIDTHON, PRIDTO);
        });
    });

    // Đăng ký sự kiện thay đổi cho các phần tử xã
    xaTriggers.forEach(id => {
        $("#" + id).change(() => {
            func_check_element(PRIDXA, PRIDTHON, 0, 7, 0, 6, 6);
            func_check_element(PRIDXA, PRIDTO, 0, 6, 7, 6, 15);
        });
    });

    // Đăng ký sự kiện thay đổi cho các phần tử thôn
    thonTriggers.forEach(id => {
        $("#" + id).change(() => {
            func_check_element(PRIDTHON, PRIDTO, 0, 8, 7, 8, 15);
        });
    });

    function updateAll(pgd, xa, thon, to) {
        func_check_element(pgd, xa, 2, 4, 2, 4, 6);
        func_check_element(pgd, thon, 2, 4, 0, 4, 6);
        func_check_element(pgd, to, 2, 4, 2, 4, 15);
    }

    function func_check_element(paElement, subElment, isatrt1, iend1, isatrt2, iend2, strlen) {
        paElement.forEach(value1PA => {
            if ($("#" + value1PA).length) {
                subElment.forEach(valueSUB => {
                    if ($("#" + valueSUB).length) {
                        func_clear_element(valueSUB);
                        func_reload_dm(value1PA, valueSUB, isatrt1, iend1, isatrt2, iend2, strlen);
                        func_beautiful_element(valueSUB);
                    }
                });
            }
        });
    }

    function func_reload_dm(idelm, idels, str1, end1, str2, end2, strlen) {
        let var1 = $("#" + idelm + " option:selected").val().substr(str1, end1);
        if (var1.trim() === '000000') {
            if (PRIDXA.includes(idelm)) {
                PRIDPGD.forEach(v => $("#" + v).length && $("#" + v).change());
                func_addall_element(idelm);
            }
            if (PRIDTHON.includes(idelm)) {
                PRIDXA.forEach(v => $("#" + v).length && $("#" + v).change());
                func_addall_element(idelm);
            }
        }
        $("#" + idels + "_DATA > option").each(function () {
            let text = $(this).text();
            let var2 = text.slice(-strlen).substr(str2, end2);
            if (var1.trim() === var2.trim()) {
                $("#" + idels).prepend(`<option value="${$(this).val()}">${text.slice(0, text.length - strlen)}</option>`);
            }
        });
    }

    function func_clear_element(id) {
        $("#" + id).empty();
    }

    function func_beautiful_element(id) {
        let options = $("#" + id + " > option").detach().sort((a, b) => $(a).val().localeCompare($(b).val()));
        options.appendTo("#" + id);
        $("#" + id).prepend("<option value='000000' selected='selected'>------Tất cả-----</option>");
    }

    function func_addall_element(elm) {
        if (PRIDXA.includes(elm)) {
            [...PRIDTHON, ...PRIDTO].forEach(id => {
                if ($("#" + id).length) {
                    $("#" + id).find("option[value='000000']").remove();
                    $("#" + id).prepend("<option value='000000' selected='selected'>------Tất cả-----</option>");
                }
            });
        }
        if (PRIDTHON.includes(elm)) {
            PRIDTO.forEach(id => {
                if ($("#" + id).length) {
                    $("#" + id).find("option[value='000000']").remove();
                    $("#" + id).prepend("<option value='000000' selected='selected'>------Tất cả-----</option>");
                }
            });
        }
    }

    // Gọi lại khi load trang
    posTriggers.forEach(id => $("#" + id).change());
});
