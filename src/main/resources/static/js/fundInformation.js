// ===== 샘플 데이터 생성 =====
const funds = [];
for (let i = 1; i <= 50; i++) {
    funds.push({
        no: i,
        title: `미래에셋글로벌코어테크EMP증권투자신탁(${i})종류A`,
        file: "#",
        view: "#"
    });
}

const rowsPerPage = 10;
let currentPage = 1;

const tbody = document.getElementById("fund-list");
const pagination = document.getElementById("pagination");

// ===== 테이블 렌더링 =====
function renderTable(page) {
    currentPage = page;
    tbody.innerHTML = "";

    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const pageData = funds.slice(start, end);

    pageData.forEach(f => {
        tbody.innerHTML += `
      <tr>
        <td>${f.no}</td>
        <td class="title"><a href="#">${f.title}</a></td>
        <td><button class="btn-download">📄 다운로드</button></td>
        <td><button class="btn-view">변경사항보기</button></td>
      </tr>`;
    });

    renderPagination();
}

// ===== 페이지네이션 =====
function renderPagination() {
    pagination.innerHTML = "";
    const totalPages = Math.ceil(funds.length / rowsPerPage);

    pagination.innerHTML += `<button ${currentPage===1?"disabled":""} onclick="changePage(1)">&laquo;</button>`;
    pagination.innerHTML += `<button ${currentPage===1?"disabled":""} onclick="changePage(${currentPage-1})">&lt;</button>`;

    for (let i = 1; i <= totalPages; i++) {
        if (i === currentPage)
            pagination.innerHTML += `<button class="active">${i}</button>`;
        else
            pagination.innerHTML += `<button onclick="changePage(${i})">${i}</button>`;
    }

    pagination.innerHTML += `<button ${currentPage===totalPages?"disabled":""} onclick="changePage(${currentPage+1})">&gt;</button>`;
    pagination.innerHTML += `<button ${currentPage===totalPages?"disabled":""} onclick="changePage(${totalPages})">&raquo;</button>`;
}

function changePage(p) {
    renderTable(p);
}

renderTable(1);

// ===== 모달 제어 =====
const modalOverlay = document.getElementById('modal-overlay');
const modalClose = document.getElementById('modal-close');
const modalContent = document.getElementById('modal-content');

// “변경사항보기” 클릭 시 모달 표시
document.addEventListener('click', e => {
    if (e.target.classList.contains('btn-view')) {
        e.preventDefault();
        const fundTitle = e.target.closest('tr').querySelector('.title a').textContent;
        modalContent.innerHTML = `<b>${fundTitle}</b><br>DB 쿼리 추가 후 작성하기.`;
        modalOverlay.style.display = 'flex';
    }
});

// 닫기 버튼
modalClose.addEventListener('click', () => {
    modalOverlay.style.display = 'none';
});

// 배경 클릭 시 닫기
modalOverlay.addEventListener('click', e => {
    if (e.target === modalOverlay) modalOverlay.style.display = 'none';
});
