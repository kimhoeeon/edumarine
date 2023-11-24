window.onload = function () { buildCalendar(); }    // 웹 페이지가 로드되면 buildCalendar 실행

let nowMonth = new Date();  // 현재 달을 페이지를 로드한 날의 달로 초기화
let today = new Date();     // 페이지를 로드한 날짜를 저장
today.setHours(0, 0, 0, 0);    // 비교 편의를 위해 today의 시간을 초기화

// 날짜와 이벤트 배열을 저장하는 객체
let events = {
    "2023-11-01": [
        { text: "[장기]해상엔진테크니션(선외기 및 선내기 통합)", className: "edu01", link: "/apply/eduApply01.do"},
        { text: "[단기]해상엔진 자가정비(선외기)", className: "edu03" }
    ],
    "2023-11-02": [
        { text: "[장기]FRP 레저보트 선체 정비 테크니션", className: "edu02" }
    ],
    "2023-11-03": [
        { text: "[단기]해상엔진 자가정비(선외기)", className: "edu03" }
    ],
    "2023-11-04": [
        { text: "[단기]해상엔진 자가정비(선내기)", className: "edu04", link: "/apply/eduApply04.do" }
    ],
    "2023-11-05": [
        { text: "[단기]해상엔진 자가정비(세일요트)", className: "edu05" }
    ]
};


// 달력 생성 : 해당 달에 맞춰 테이블을 만들고, 날짜를 채워 넣는다.
function buildCalendar() {

    let firstDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth(), 1);     // 이번달 1일
    let lastDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, 0);  // 이번달 마지막날

    let tbody_Calendar = document.querySelector(".calendar > tbody");
    document.getElementById("calYear").innerText = nowMonth.getFullYear() + "년";             // 연도 숫자 갱신
    document.getElementById("calMonth").innerText = nowMonth.getMonth() + 1 + "월";  // 월 숫자 갱신

    while (tbody_Calendar.rows.length > 0) {                        // 이전 출력결과가 남아있는 경우 초기화
        tbody_Calendar.deleteRow(tbody_Calendar.rows.length - 1);
    }
    

    
    let nowRow = tbody_Calendar.insertRow();        // 첫번째 행 추가           

    for (let j = 0; j < firstDate.getDay(); j++) {  // 이번달 1일의 요일만큼
        let nowColumn = nowRow.insertCell();        // 열 추가
    }

    for (let nowDay = firstDate; nowDay <= lastDate; nowDay.setDate(nowDay.getDate() + 1)) {
        let nowColumn = nowRow.insertCell();
        let dateWrapper = document.createElement("div"); // 새로운 div 태그 생성
        let newDIV = document.createElement("div"); // 새로운 div 태그 생성
    
        newDIV.innerText = nowDay.getDate(); // div 태그에 날짜 입력
    
        // 다음은 날짜별로 클래스를 지정하는 부분
        if (nowDay.getDay() == 6) {
            nowRow = tbody_Calendar.insertRow();
        }
    
        if (nowDay < today) {
            dateWrapper.className = "pastDay";
            dateWrapper.onclick = function () { choiceDate(newDIV); }
        } else if (nowDay.getFullYear() == today.getFullYear() && nowDay.getMonth() == today.getMonth() && nowDay.getDate() == today.getDate()) {
            dateWrapper.className = "today";
            dateWrapper.onclick = function () { choiceDate(newDIV); }
        } else {
            dateWrapper.className = "futureDay";
            dateWrapper.onclick = function () { choiceDate(newDIV); }
        }
    
        dateWrapper.appendChild(newDIV); // 날짜를 감싸는 div에 날짜 div 추가
    
        nowColumn.appendChild(dateWrapper); // 테이블 셀에 감싸는 div 추가
    
        let dateString = nowDay.getFullYear() + '-' + (nowDay.getMonth() + 1).toString().padStart(2, '0') + '-' + nowDay.getDate().toString().padStart(2, '0');
    
        // 이벤트 정보가 있는 경우 해당 날짜에 이벤트를 추가
        if (events[dateString]) {
            for (let i = 0; i < events[dateString].length; i++) {
                let scheduleParagraph = document.createElement("p");
                scheduleParagraph.innerText = events[dateString][i].text;
                scheduleParagraph.className = events[dateString][i].className; // 클래스 추가
                dateWrapper.appendChild(scheduleParagraph); // p 태그를 날짜를 감싸는 div에 추가
            }
        }
    }

}



// 날짜 선택
function choiceDate(newDIV) {
    // 이전에 선택된 날짜가 있다면 클래스 제거
    let previousChoiceDay = document.querySelector(".choiceDay");
    if (previousChoiceDay) {
        previousChoiceDay.classList.remove("choiceDay");
    }

    // 선택한 날짜의 부모인 dateWrapper에 choiceDay 클래스 추가
    let dateWrapper = newDIV.parentElement;
    dateWrapper.classList.add("choiceDay");

    // 선택한 날짜의 날짜 텍스트 가져오기
    let selectedDateText = newDIV.innerText;

    // 선택한 날짜를 노출하는 요소에 텍스트 설정
    let choiceCalDay = document.querySelector(".choiceCalDay");
    choiceCalDay.innerHTML = "";  // 기존 내용 제거
    choiceCalDay.innerText = nowMonth.getFullYear() + "년 " + (nowMonth.getMonth() + 1) + "월 " + newDIV.innerText + "일";

    // 선택한 날짜의 이벤트 가져오기
    let dateString = nowMonth.getFullYear() + "-" + (nowMonth.getMonth() + 1) + "-" + selectedDateText.padStart(2, '0');
    let selectedEvents = events[dateString];

    // 이벤트 목록을 노출하는 요소 가져오기
    let calList = document.querySelector(".calList");

    // 기존에 있는 이벤트 목록 삭제
    calList.querySelector(".choiceSkedList").innerHTML = "";

    // 이벤트가 있는 경우 목록에 추가
    if (selectedEvents) {
        for (let i = 0; i < selectedEvents.length; i++) {
            let eventText = selectedEvents[i].text;
            let eventClassName = selectedEvents[i].className;
            let eventLink = selectedEvents[i].link; // 이벤트의 링크

            // li 요소 생성
            let eventListItem = document.createElement("li");
            eventListItem.className = eventClassName;

            // a 태그 생성
            let eventLinkElement = document.createElement("a");
            eventLinkElement.href = eventLink || "#";  // 링크가 없으면 기본값으로 "#" 설정
            eventLinkElement.innerText = eventText;

            // a 태그를 li 요소에 추가
            eventListItem.appendChild(eventLinkElement);

            // li 요소를 ul에 추가
            calList.querySelector(".choiceSkedList").appendChild(eventListItem);
        }
    }
}



// 이전달 버튼 클릭
function prevCalendar() {
    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() - 1, nowMonth.getDate());   // 현재 달을 1 감소
    buildCalendar();    // 달력 다시 생성
}
// 다음달 버튼 클릭
function nextCalendar() {
    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, nowMonth.getDate());   // 현재 달을 1 증가
    buildCalendar();    // 달력 다시 생성
}

