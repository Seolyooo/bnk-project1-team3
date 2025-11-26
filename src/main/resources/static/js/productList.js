let fundData = [];

// ====== DB 데이터 로드 ======
async function loadFundData() {
    try {
        const res = await fetch("/bnk/api/fund/list");
        const rawData = await res.json();

        console.log("DB에서 받은 데이터(raw):", rawData);

        // ===== DB 위험등급을 화면 카테고리로 변환 =====
        fundData = rawData.map(f => {
            let category = "all";

            switch (f.investgrade) {
                case "매우 낮은 위험":
                    category = "safe";          // 안정형
                    break;
                case "낮은 위험":
                    category = "stable";        // 안정추구형
                    break;
                case "중간 위험":
                    category = "neutral";       // 위험중립형
                    break;
                case "높은 위험":
                    category = "dividend";      // 적극투자형
                    break;
                case "매우 높은 위험":
                    category = "ipo";           // 공격투자형
                    break;
                default:
                    category = "all";
            }

            return { ...f, category };
        });

        console.log("카테고리 변환 후 데이터:", fundData);

        renderFundList("all");
    } catch (error) {
        console.error("펀드 데이터 불러오기 실패", error);
    }
}

// ====== 테이블 렌더링 ======
function renderFundList(category = "all") {
    const tbody = document.getElementById("fund-list");
    tbody.innerHTML = "";

    const filtered =
        category === "all"
            ? fundData
            : fundData.filter(f => f.category === category);

    if (filtered.length === 0) {
        tbody.innerHTML =
            `<tr><td colspan="6">해당 조건에 맞는 펀드가 없습니다.</td></tr>`;
        return;
    }

    filtered.forEach(fund => {
        tbody.innerHTML += `
          <tr>
            <td class="fund-name">

              <a href="/bnk/fund/productDetail/${fund.fundcode}">
                ${fund.fundName
        ?? fund.fundNm
        ?? fund.fundshortcode
        ?? fund.fundcode}
              </a>

              <div class="tag-wrap">
                <span class="tag">${fund.investgrade || ""}</span>
              </div>

              <div class="desc">${fund.fundfeature || ""}</div>

            </td>

            <td>${fund.perf1M ?? "-"}</td>
            <td>${fund.perf3M ?? "-"}</td>
            <td>${fund.perf6M ?? "-"}</td>
            <td>${fund.perf12M ?? "-"}</td>

            <td>
              <button class="btn-join"
                onclick="location.href='/fund/join?fundNo=${fund.fundcode}'">
                인터넷가입
              </button>
              <span class="sub-btn">스마트폰가입</span>
            </td>
          </tr>`;
    });
}

// ====== 상단 탭 클릭 이벤트 ======
document.querySelectorAll(".tab").forEach(tab => {
    tab.addEventListener("click", () => {
        document.querySelectorAll(".tab").forEach(t => t.classList.remove("active"));
        tab.classList.add("active");

        const type = tab.dataset.type;
        document.getElementById("title").textContent = tab.textContent;

        if (type === "fund") {
            document.getElementById("fund-filter").style.display = "flex";
            renderFundList("all");
        } else {
            document.getElementById("fund-filter").style.display = "none";
            document.getElementById("fund-list").innerHTML =
                `<tr><td colspan='6'>${tab.textContent} DB 조회 필요</td></tr>`;
        }
    });
});

// ====== 필터 버튼 클릭 이벤트 ======
document.querySelectorAll("#fund-filter button").forEach(btn => {
    btn.addEventListener("click", () => {
        document.querySelectorAll("#fund-filter button")
            .forEach(b => b.classList.remove("active"));
        btn.classList.add("active");

        const category = btn.dataset.category;
        renderFundList(category);
    });
});

// ====== 페이지 로드 시 DB 데이터 호출 ======
document.addEventListener("DOMContentLoaded", loadFundData);
