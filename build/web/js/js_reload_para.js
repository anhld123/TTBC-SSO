$(document).ready(function () {
    const $pgd = $("#PARA_MAPGD, #PV_MAPGD, #PARA_MAPGD_MAPGD, #PV_POS_CD, #PV_POS_CD_MAPGD, #POS_CD, #PARA_POS");
    const $xa = $("#PARA_MAXA, #PV_MAXA, #PV_MAXAD, #PARA_COMMUNEID");
    const $to = $("#PARA_MATO, #PV_MATO");
    const $thon = $("#PARA_MATHON, #PV_MATHON");

    const originalXaOptions = $xa.length ? $xa.find("option").clone() : null;
    const originalToOptions = $to.length ? $to.find("option").clone() : null;
    const originalThonOptions = $thon.length ? $thon.find("option").clone() : null;

    function filterToByPgdXa(pgdVal, xaVal) {
        if (!$to.length || !originalToOptions)
            return;

        $to.each(function () {
            const $thisTo = $(this).empty();

            originalToOptions.each(function () {
                const toText = $(this).text().trim();
                const dollarIndex = toText.indexOf("$");

                if (dollarIndex >= 6 && toText.length >= dollarIndex + 6) {
                    const pgdCodeInText = toText.substring(dollarIndex - 6, dollarIndex);
                    const xaCodeInText = toText.substring(dollarIndex + 1, dollarIndex + 7);

                    const pgdMatch = pgdVal === "000000" || pgdCodeInText === pgdVal;
                    const xaMatch = xaVal === "000000" || xaCodeInText === xaVal;

                    if (pgdMatch && xaMatch) {
                        const displayText = toText.slice(0, -15).trim();
                        $thisTo.append(`<option value="${$(this).val()}">${displayText}</option>`);
                    }
                }
            });

            $thisTo.prepend(`<option value="000000" selected>--Tất cả--</option>`);
        });
    }

    function filterThonByPgdXa(pgdVal, xaVal) {
        if (!$thon.length || !originalThonOptions)
            return;

        $thon.each(function () {
            const $thisThon = $(this).empty();

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
        if (!thonVal || !$to.length || !originalToOptions)
            return;

        $to.each(function () {
            const $thisTo = $(this).empty();

            originalToOptions.each(function () {
                const toText = $(this).text().trim();
                const toThonCode = toText.slice(-8);

                if (thonVal === "00000000" || toThonCode === thonVal) {
                    const displayText = toText.slice(0, -15).trim();
                    $thisTo.append(`<option value="${$(this).val()}">${displayText}</option>`);
                }
            });

            $thisTo.prepend(`<option value="000000" selected>--Tất cả--</option>`);
        });
    }

    // Trường hợp chỉ có PGD và TỔ
    function filterToByPgdOnly(pgdVal) {
        if (!$to.length || !originalToOptions)
            return;

        $to.each(function () {
            const $thisTo = $(this).empty();

            originalToOptions.each(function () {
                const toText = $(this).text().trim();
                const dollarIndex = toText.indexOf("$");

                if (dollarIndex >= 6) {
                    const pgdCodeInText = toText.substring(dollarIndex - 6, dollarIndex);

                    if (pgdVal === "000000" || pgdCodeInText === pgdVal) {
                        const displayText = toText.slice(0, -15).trim();
                        $thisTo.append(`<option value="${$(this).val()}">${displayText}</option>`);
                    }
                }
            });

            $thisTo.prepend(`<option value="000000" selected>--Tất cả--</option>`);
        });
    }

    // --- Khi PGD thay đổi ---
    if ($pgd.length) {
        $pgd.on("change", function () {
            const pgdVal = $(this).val() ? $(this).val().trim() : "000000";

            // Nếu có danh sách xã thì lọc xã
            if ($xa.length && originalXaOptions) {
                $xa.each(function () {
                    const $thisXa = $(this).empty();

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
                });

                const xaValRaw = $xa.first().val();
                const xaVal = xaValRaw ? xaValRaw.trim() : "000000";

                filterToByPgdXa(pgdVal, xaVal);
                filterThonByPgdXa(pgdVal, xaVal);
            } else {
                // Chỉ có PGD và tổ → gọi hàm riêng
                filterToByPgdOnly(pgdVal);
            }
        });
    }

    // --- Khi xã thay đổi ---
    if ($xa.length && $pgd.length) {
        $xa.on("change", function () {
            const xaVal = $(this).val() ? $(this).val().trim() : "000000";
            const pgdValRaw = $pgd.first().val();
            const pgdVal = pgdValRaw ? pgdValRaw.trim() : "000000";

            filterToByPgdXa(pgdVal, xaVal);
            filterThonByPgdXa(pgdVal, xaVal);
        });
    }

    // --- Khi thôn thay đổi thì lọc tổ theo thôn ---
    if ($thon.length && $to.length) {
        $thon.on("change", function () {
            const thonVal = $(this).val() ? $(this).val().trim() : "00000000";
            filterToByThon(thonVal);
        });
    }

    // --- Trigger mặc định khi trang tải ---
    if ($pgd.length) {
        $pgd.trigger("change");
    }
});
