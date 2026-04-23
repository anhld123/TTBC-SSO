document.addEventListener("keydown", function (e) {
    if (e.key === "F12" ||
            (e.ctrlKey && e.key === "u") ||
            (e.ctrlKey && e.shiftKey && e.key === "I")) {
        e.preventDefault();
    }
});

document.addEventListener("contextmenu", function (e) {
    e.preventDefault();
});