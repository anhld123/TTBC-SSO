window.onload = function () {

    var canvas = document.getElementById("snowCanvas_tet");
    if (!canvas)
        return;
    var ctx = canvas.getContext("2d");

    var w = canvas.width = window.innerWidth;
    var h = canvas.height = window.innerHeight;

    window.onresize = function () {
        w = canvas.width = window.innerWidth;
        h = canvas.height = window.innerHeight;
    };

    /* ========= TEXT ========= */
    var TEXT_MESSAGE = "CHÚC MỪNG NĂM MỚI 2026";
    var TEXT_Y = 90;

    /* ========= MẢNG ========= */
    var fireworks = [];
    var particles = [];
    var flowers = [];
    var lanterns = [];
    var items = [];

    /* ========= MÀU ========= */
    var fwColors = ["#ff3b3b", "#ffd700", "#ffffff", "#ff8c00"];

    /* ================= PHÁO HOA ================= */
    function Firework(x, y, text) {
        this.x = x || Math.random() * w;
        this.y = h;
        this.targetY = y || Math.random() * h * 0.6 + 80;
        this.text = text || false;
        this.speed = Math.random() * 3 + 6;
        this.color = fwColors[Math.floor(Math.random() * fwColors.length)];
        this.exploded = false;
    }

    Firework.prototype.update = function () {
        this.y -= this.speed;
        if (this.y <= this.targetY) {
            this.explode();
            this.exploded = true;
        }
    };

    Firework.prototype.draw = function () {
        ctx.fillStyle = this.color;
        ctx.fillRect(this.x, this.y, 2, 2);
    };

    Firework.prototype.explode = function () {
        var count = this.text ? 220 : 120;
        for (var i = 0; i < count; i++) {
            particles.push(new Particle(this.x, this.y, this.color));
        }
    };

    function Particle(x, y, color) {
        var a = Math.random() * Math.PI * 2;
        var s = Math.random() * 4 + 1;
        this.vx = Math.cos(a) * s;
        this.vy = Math.sin(a) * s;
        this.x = x;
        this.y = y;
        this.life = 90;
        this.alpha = 1;
        this.color = color;
    }

    Particle.prototype.update = function () {
        this.vy += 0.04;
        this.x += this.vx;
        this.y += this.vy;
        this.life--;
        this.alpha -= 0.015;
    };

    Particle.prototype.draw = function () {
        ctx.globalAlpha = this.alpha;
        ctx.fillStyle = this.color;
        ctx.fillRect(this.x, this.y, 2, 2);
        ctx.globalAlpha = 1;
    };

    /* ================= CHỮ PHÁO HOA ================= */
    function fireworkText() {
        for (var i = 0; i < TEXT_MESSAGE.length; i++) {
            fireworks.push(new Firework(
                    w / 2 - TEXT_MESSAGE.length * 12 + i * 24,
                    TEXT_Y,
                    true
                    ));
        }
    }
    fireworkText();
    setInterval(fireworkText, 16000);

    /* ================= HOA MAI / HOA ĐÀO ================= */
    function Flower() {
        this.x = Math.random() * w;
        this.y = Math.random() * h;
        this.r = Math.random() * 3 + 2;
        this.speed = Math.random() * 0.6 + 0.3;
        this.rot = Math.random() * Math.PI * 2;
        this.rotSpeed = (Math.random() - 0.5) * 0.01;
        this.type = Math.random() > 0.5 ? "dao" : "mai";
    }

    Flower.prototype.update = function () {
        this.y += this.speed;
        this.rot += this.rotSpeed;
        if (this.y > h)
            this.y = -20;
    };

    Flower.prototype.draw = function () {
        ctx.save();
        ctx.translate(this.x, this.y);
        ctx.rotate(this.rot);

        var petalColor = this.type === "dao" ? "#ff8fb1" : "#ffd700";

        ctx.fillStyle = petalColor;

        for (var i = 0; i < 5; i++) {
            ctx.beginPath();
            ctx.rotate(Math.PI * 2 / 5);
            ctx.moveTo(0, 0);
            ctx.quadraticCurveTo(4, -4, 0, -this.r * 3);
            ctx.quadraticCurveTo(-4, -4, 0, 0);
            ctx.fill();
        }

        ctx.fillStyle = "#ffcc00";
        ctx.beginPath();
        ctx.arc(0, 0, 1.5, 0, Math.PI * 2);
        ctx.fill();

        ctx.restore();
    };

    /* ================= LỒNG ĐÈN ================= */
    function Lantern() {
        this.x = Math.random() * w;
        this.y = h + 40;
        this.speed = Math.random() * 0.3 + 0.2;
        this.swing = Math.random() * Math.PI * 2;
    }

    Lantern.prototype.draw = function () {

        ctx.save();
        ctx.translate(this.x + Math.sin(this.swing) * 6, this.y);

        // ánh sáng trong
        var glow = ctx.createRadialGradient(0, 0, 2, 0, 0, 16);
        glow.addColorStop(0, "rgba(255,200,100,0.9)");
        glow.addColorStop(1, "rgba(255,0,0,0.2)");

        ctx.fillStyle = glow;
        ctx.beginPath();
        ctx.arc(0, 0, 16, 0, Math.PI * 2);
        ctx.fill();

        // thân lồng đèn
        ctx.fillStyle = "#c40000";
        ctx.beginPath();
        ctx.ellipse(0, 0, 12, 16, 0, 0, Math.PI * 2);
        ctx.fill();

        // múi dọc
        ctx.strokeStyle = "rgba(255,215,0,0.6)";
        for (var i = -10; i <= 10; i += 5) {
            ctx.beginPath();
            ctx.moveTo(i, -14);
            ctx.quadraticCurveTo(i * 0.3, 0, i, 14);
            ctx.stroke();
        }

        // viền trên & dưới
        ctx.fillStyle = "#ffd700";
        ctx.fillRect(-10, -18, 20, 4);
        ctx.fillRect(-10, 14, 20, 4);

        // tua rua
        ctx.strokeStyle = "#ffd700";
        for (i = -6; i <= 6; i += 3) {
            ctx.beginPath();
            ctx.moveTo(i, 18);
            ctx.lineTo(i, 26);
            ctx.stroke();
        }

        ctx.restore();
    };
    Lantern.prototype.update = function () {
        this.y -= this.speed;          // bay lên
        this.swing += 0.02;            // đung đưa

        if (this.y < -80) {
            this.y = h + 80;
            this.x = Math.random() * w;
        }
    };
    /* ================= LÌ XÌ / XU ================= */
    function Item() {
        this.x = Math.random() * w;
        this.y = Math.random() * h;
        this.speed = Math.random() * 0.8 + 0.4;
        this.rot = Math.random() * Math.PI * 2;
        this.rotSpeed = (Math.random() - 0.5) * 0.02;

        var rnd = Math.random();
        if (rnd < 0.25)
            this.type = "lixi";
        else if (rnd < 0.5)
            this.type = "coin";
        else if (rnd < 0.75)
            this.type = "banhchung";
    }


    Item.prototype.update = function () {
        this.y += this.speed;
        this.rot += this.rotSpeed;
        if (this.y > h)
            this.y = -30;
    };

    Item.prototype.draw = function () {
        ctx.save();
        ctx.translate(this.x + 10, this.y + 13);
        ctx.rotate(this.rot);
        ctx.translate(-10, -13);

        if (this.type === "lixi") {
            ctx.fillStyle = "#c40000";
            ctx.fillRect(1, 1, 18, 26);
            ctx.strokeStyle = "#ffd700";
            ctx.lineWidth = 2;
            ctx.strokeRect(1, 1, 18, 26);
            ctx.fillStyle = "#ffd700";
            ctx.font = "bold 12px serif";
            ctx.textAlign = "center";
            ctx.fillText("福", 10, 18);
        } else if (this.type === "banhchung") {
            // thân bánh
            ctx.fillStyle = "#2e7d32";
            ctx.fillRect(2, 2, 20, 20);

            // viền
            ctx.strokeStyle = "#1b5e20";
            ctx.lineWidth = 2;
            ctx.strokeRect(2, 2, 20, 20);

            // dây lạt ngang
            ctx.strokeStyle = "#c8e6c9";
            ctx.beginPath();
            ctx.moveTo(2, 12);
            ctx.lineTo(22, 12);
            ctx.stroke();

            // dây lạt dọc
            ctx.beginPath();
            ctx.moveTo(12, 2);
            ctx.lineTo(12, 22);
            ctx.stroke();
        } else {
            var g = ctx.createRadialGradient(10, 10, 2, 10, 10, 10);
            g.addColorStop(0, "#fff4b0");
            g.addColorStop(1, "#d4a300");
            ctx.fillStyle = g;
            ctx.beginPath();
            ctx.arc(10, 10, 8, 0, Math.PI * 2);
            ctx.fill();
            ctx.clearRect(7, 7, 6, 6);
            ctx.strokeStyle = "#ffd700";
            ctx.stroke();
        }
        ctx.restore();
    };

    /* ================= KHỞI TẠO ================= */
    for (var i = 0; i < 80; i++)
        flowers.push(new Flower());
    for (var j = 0; j < 5; j++)
        lanterns.push(new Lantern());
    for (var k = 0; k < 24; k++)
        items.push(new Item());

    /* ================= LOOP ================= */
    function loop() {
        ctx.clearRect(0, 0, w, h);

        if (Math.random() < 0.05)
            fireworks.push(new Firework());

        for (var i = fireworks.length - 1; i >= 0; i--) {
            fireworks[i].update();
            fireworks[i].draw();
            if (fireworks[i].exploded)
                fireworks.splice(i, 1);
        }

        for (var p = particles.length - 1; p >= 0; p--) {
            particles[p].update();
            particles[p].draw();
            if (particles[p].life <= 0)
                particles.splice(p, 1);
        }

        flowers.forEach(f => {
            f.update();
            f.draw();
        });

        lanterns.forEach(l => {
            l.update();
            l.draw();
        });

        items.forEach(it => {
            it.update();
            it.draw();
        });

        requestAnimationFrame(loop);
    }

    loop();
};

(function () {
    const canvas = document.getElementById("snowCanvas_noel");
    if (!canvas)
        return;

    const ctx = canvas.getContext("2d");
    let w, h;

    function resize() {
        w = canvas.width = window.innerWidth;
        h = canvas.height = window.innerHeight;
    }
    resize();
    window.addEventListener("resize", resize);

    const snowflakes = [];
    const MAX = 140; // tổng số bông tuyết

    function createFlake(type) {
        return {
            x: Math.random() * w,
            y: Math.random() * h,
            r: Math.random() * 2 + 1,
            speed: Math.random() * 0.8 + 0.4,
            drift: Math.random() * 0.6 + 0.2,
            rot: Math.random() * Math.PI * 2,
            rotSpeed: (Math.random() - 0.5) * 0.02,
            type: type // "dot" | "crystal"
        };
    }

    // 70% tuyết tròn, 30% tuyết pha lê
    for (let i = 0; i < MAX; i++) {
        snowflakes.push(createFlake(Math.random() < 0.7 ? "dot" : "crystal"));
    }

    function drawCrystal(x, y, r, rot) {
        ctx.save();
        ctx.translate(x, y);
        ctx.rotate(rot);
        ctx.strokeStyle = "rgba(255,255,255,0.8)";
        ctx.lineWidth = 1;

        ctx.beginPath();
        for (let i = 0; i < 6; i++) {
            ctx.moveTo(0, 0);
            ctx.lineTo(0, -r * 3);
            ctx.rotate(Math.PI / 3);
        }
        ctx.stroke();
        ctx.restore();
    }

    function draw() {
        ctx.clearRect(0, 0, w, h);

        snowflakes.forEach(f => {
            if (f.type === "dot") {
                // tuyết tròn
                ctx.fillStyle = "rgba(255,255,255,0.85)";
                ctx.beginPath();
                ctx.arc(f.x, f.y, f.r, 0, Math.PI * 2);
                ctx.fill();
            } else {
                // tuyết pha lê
                drawCrystal(f.x, f.y, f.r, f.rot);
            }
        });

        update();
    }

    function update() {
        snowflakes.forEach(f => {
            f.y += f.speed;
            f.x += Math.sin(f.y * 0.01) * f.drift;
            f.rot += f.rotSpeed;

            if (f.y > h) {
                f.y = -20;
                f.x = Math.random() * w;
            }
        });
    }

    function loop() {
        draw();
        requestAnimationFrame(loop);
    }
    loop();
})();