/*
 * Nguyễn Phú Vinh (0849.358.358) - 03/03/2021 -> cha đẻ
 * Lê Đức Anh sửa sau sát nhập
 * Xử lý báo cáo khi chọn đơn vị
 */
$(document).ready(function () {
    const $pgd = $("#PARA_MAPGD, #PV_MAPGD, #PARA_MAPGD_MAPGD, #PV_POS_CD, #PV_POS_CD_MAPGD, #POS_CD, #PARA_POS");
    const $xa = $("#PARA_MAXA, #PV_MAXA, #PV_MAXAD, #PARA_COMMUNEID");
    const $to = $("#PARA_MATO, #PV_MATO");
    const $thon = $("#PARA_MATHON, #PV_MATHON");

    const originalXaOptions = $xa.find("option").clone();
    const originalToOptions = $to.find("option").clone();
    const originalThonOptions = $thon.find("option").clone();

    function filterToByPgdXa(pgdVal, xaVal) {
        $to.each(function () {
            const $thisTo = $(this);
            $thisTo.empty();

            originalToOptions.each(function () {
                const toText = $(this).text().trim();
                const dollarIndex = toText.indexOf("$");

                if (dollarIndex >= 6 && toText.length >= dollarIndex + 6) {
                    const pgdCodeInText = toText.substring(dollarIndex - 6, dollarIndex);
                    const xaCodeInText = toText.substring(dollarIndex + 1, dollarIndex + 7);

                    const pgdMatch = pgdVal === "000000" || pgdCodeInText === pgdVal;
                    const xaMatch = xaVal === "000000" || xaCodeInText === xaVal;

                    if (pgdMatch && xaMatch) {
                        const displayText = toText.slice(0, -15).trim(); // bỏ 15 ký tự cuối
                        $thisTo.append(`<option value="${$(this).val()}">${displayText}</option>`);
                    }
                }
            });

            $thisTo.prepend(`<option value="000000" selected>--Tất cả--</option>`);
        });
    }

    function filterThonByPgdXa(pgdVal, xaVal) {
        $thon.each(function () {
            const $thisThon = $(this);
            $thisThon.empty();

            originalThonOptions.each(function () {
                const thonText = $(this).text().trim();
                const matches = thonText.match(/\$(\d{6})\s*\$(\d{6})/);

                if (matches && matches.length === 3) {
                    const pgdCode = matches[1];
                    const xaCode = matches[2];

                    const pgdMatch = pgdVal === "000000" || pgdCode === pgdVal;
                    const xaMatch = xaVal === "000000" || xaCode === xaVal;

                    if (pgdMatch && xaMatch) {
                        const displayText = thonText.slice(0, thonText.indexOf("$")).trim();
                        $thisThon.append(`<option value="${$(this).val()}">${displayText}</option>`);
                    }
                }
            });

            $thisThon.prepend(`<option value="00000000" selected>--Tất cả--</option>`);
        });
    }

    function filterToByThon(thonVal) {
        if (!thonVal)
            return;
        $to.each(function () {
            const $thisTo = $(this);
            $thisTo.empty();

            originalToOptions.each(function () {
                const toText = $(this).text().trim();
                const toThonCode = toText.slice(-8); // Lấy 8 ký tự cuối để so sánh mã thôn

                const match = thonVal === "00000000" || toThonCode === thonVal;
                if (match) {
                    const displayText = toText.slice(0, -15).trim(); // Bỏ 15 ký tự cuối
                    $thisTo.append(`<option value="${$(this).val()}">${displayText}</option>`);
                }
            });

            $thisTo.prepend(`<option value="000000" selected>--Tất cả--</option>`);
        });
    }


    $pgd.on("change", function () {
        const pgdVal = $(this).val().trim();

        $xa.each(function () {
            const $thisXa = $(this);
            $thisXa.empty();

            originalXaOptions.each(function () {
                const val = $(this).val();
                const text = $(this).text().trim();
                const suffix = text.slice(-6);

                if (suffix === pgdVal || pgdVal === "000000") {
                    const displayText = text.slice(0, -6).trim();
                    $thisXa.append(`<option value="${val}">${displayText}</option>`);
                }
            });

            $thisXa.prepend(`<option value="000000" selected>--Tất cả--</option>`);
            $thisXa.trigger("change");
        });

        // Gọi lọc thôn
        const xaVal = $xa.first().val().trim();
        filterThonByPgdXa(pgdVal, xaVal);
    });

    $xa.on("change", function () {
        const xaVal = $(this).val().trim();
        const pgdVal = $pgd.first().val().trim();

        filterToByPgdXa(pgdVal, xaVal);
        filterThonByPgdXa(pgdVal, xaVal);
    });

    $thon.on("change", function () {
        const thonVal = $(this).val().trim();
        filterToByThon(thonVal);
    });

    $pgd.trigger("change");
});
