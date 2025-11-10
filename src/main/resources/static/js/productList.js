// ====== 데이터 정의 ======
const fundData = [
    {
        tag: "매우높은위험",
        name: "여기에 DB데이터 불러와야함",
        desc: `AI, 로봇, 반도체, 전기차 등 미래 성장 산업 중심의 기술주 투자`,
        rates: [18.81, 46.80, 94.51, 79.68],
        link: "detail_mirae_core.html",
        category: "recommend"
    },
    {
        tag: "높은위험",
        name: "여기에 DB데이터 불러와야함",
        desc: `청년층의 장기 투자 혜택과 소득공제를 지원하는 성장형 펀드`,
        rates: [18.81, 46.76, 94.42, 79.62],
        link: "detail_mirae_youth.html",
        category: "ipo"
    },
    {
        tag: "보통위험",
        name: "KB배당인컴증권투자신탁(주식) 종류A",
        desc: `안정적인 배당 수익과 중장기 자본이득을 함께 추구하는 펀드`,
        rates: [8.11, 16.45, 23.21, 30.55],
        link: "detail_kb_dividend.html",
        category: "dividend"
    },
    {
        tag: "중위험중수익",
        name: "신한위험중립형밸런스펀드",
        desc: `주식과 채권을 균형 있게 구성하여 리스크를 낮춘 밸런스형 펀드`,
        rates: [6.21, 11.43, 15.98, 20.33],
        link: "detail_shinhan_neutral.html",
        category: "neutral"
    },
    {
        tag: "낮은위험",
        name: "우리안정추구형증권펀드",
        desc: `채권 비중을 높여 안정적인 이자수익을 추구하는 펀드`,
        rates: [2.15, 3.87, 5.24, 6.45],
        link: "detail_woori_stable.html",
        category: "stable"
    },
    {
        tag: "안정형",
        name: "하나안정형자산배분펀드",
        desc: `국공채 및 고등급 채권 위주의 초저위험 펀드`,
        rates: [0.95, 1.87, 3.12, 3.80],
        link: "detail_hana_safe.html",
        category: "safe"
    }
];

// ====== 테이블 렌더링 ======
function renderFundList(category = "all") {
    const tbody = document.getElementById("fund-list");
    tbody.innerHTML = "";

    const filtered = category === "all"
        ? fundData
        : fundData.filter(f => f.category === category);

    if (filtered.length === 0) {
        tbody.innerHTML = `<tr><td colspan="6">해당 조건에 맞는 펀드가 없습니다.</td></tr>`;
        return;
    }

    filtered.forEach(fund => {
        tbody.innerHTML += `
          <tr>
            <td class="fund-name">
              ${fund.tag ? `<span class="tag">${fund.tag}</span>` : ""}
              <a href="productDetail" target="_blank">${fund.name}</a>
              <div class="desc">${fund.desc}</div>
            </td>
            <td>${fund.rates[0]}</td>
            <td>${fund.rates[1]}</td>
            <td>${fund.rates[2]}</td>
            <td>${fund.rates[3]}</td>
            <td>
              <button class="btn-join" onclick="location.href='${fund.link}'">인터넷가입</button>
              <span class="sub-btn">스마트폰가입</span>
            </td>
          </tr>`;
    });
}

// ====== 탭 클릭 이벤트 ======
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
            document.getElementById("fund-list").innerHTML = `<tr><td colspan='6'>${tab.textContent} DB데이터 맞게</td></tr>`;
        }
    });
});

// ====== 필터 버튼 클릭 이벤트 ======
document.querySelectorAll("#fund-filter button").forEach(btn => {
    btn.addEventListener("click", () => {
        document.querySelectorAll("#fund-filter button").forEach(b => b.classList.remove("active"));
        btn.classList.add("active");
        const category = btn.dataset.category;
        renderFundList(category);
    });
});

// 첫 화면 렌더링
renderFundList("all");
