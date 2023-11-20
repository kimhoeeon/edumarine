let nowMonth = new Date(); // 현재 달을 페이지를 로드한 날의 달로 초기화
let today = new Date(); // 페이지를 로드한 날짜를 저장
today.setHours(0, 0, 0, 0); // 비교 편의를 위해 today의 시간을 초기화

window.onload = function () {
    buildCalendar();
};

function buildCalendar() {
    let firstDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth(), 1); // 이번달 1일
    let lastDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, 0); // 이번달 마지막날

    let tbody_Calendar = document.querySelector(".calendar > tbody");
    if (!tbody_Calendar) {
        console.error("Error: tbody_Calendar not found.");
        return;
    }

    document.getElementById("calYear").innerText = nowMonth.getFullYear();
    document.getElementById("calMonth").innerText = leftPad(nowMonth.getMonth() + 1);

    // 이전 출력결과가 남아있는 경우 초기화
    while (tbody_Calendar.firstChild) {
        tbody_Calendar.removeChild(tbody_Calendar.firstChild);
    }

    let currentRow = null;
    let currentDayOfWeek = firstDate.getDay(); // 이번달 1일의 요일 (0: 일요일, 1: 월요일, ..., 6: 토요일)

    for (let i = 0; i < currentDayOfWeek; i++) {
        // 첫 번째 주(행)의 남은 공간을 채우기 위해 빈 셀 추가
        let emptyCell = document.createElement("td");
        if (!currentRow) {
            currentRow = document.createElement("tr");
            tbody_Calendar.appendChild(currentRow);
        }
        currentRow.appendChild(emptyCell);
    }

    for (let nowDay = firstDate; nowDay <= lastDate; nowDay.setDate(nowDay.getDate() + 1)) {
        if (currentDayOfWeek === 0) {
            // 일요일(요일 0)인 경우 새로운 주(행) 생성
            currentRow = document.createElement("tr");
            tbody_Calendar.appendChild(currentRow);
        }

        // 날짜 셀 추가
        let nowColumn = document.createElement("td");

        let newDIV = document.createElement("div");
        let dateP = document.createElement("p");
        dateP.innerHTML = leftPad(nowDay.getDate());
        newDIV.appendChild(dateP);

        let ul = document.createElement("ul");

        // 여기서 해당 날짜에 대한 일정 정보를 가져와서 ul에 li로 추가
        let schedule = getScheduleForDate(nowDay); // 일정 정보를 가져오는 함수 (예시를 위해 가정합니다.)

        if (schedule.length > 0) {
            schedule.forEach((item) => {
                let li = document.createElement("li");
                li.innerHTML = item;
                ul.appendChild(li);
            });
        }

        newDIV.appendChild(ul);
        nowColumn.appendChild(newDIV);
        currentRow.appendChild(nowColumn);

        currentDayOfWeek = (currentDayOfWeek + 1) % 7; // 다음 요일로 이동

        if (nowDay.getFullYear() == today.getFullYear() && nowDay.getMonth() == today.getMonth() && nowDay.getDate() == today.getDate()) { // 오늘인 경우           
            newDIV.className = "today";
            newDIV.onclick = function () { choiceDate(this); }
        }
    }
}

// 이전달 버튼 클릭
function prevCalendar() {
    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() - 1, nowMonth.getDate()); // 현재 달을 1 감소
    buildCalendar(); // 달력 다시 생성
}

// 다음달 버튼 클릭
function nextCalendar() {
    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, nowMonth.getDate()); // 현재 달을 1 증가
    buildCalendar(); // 달력 다시 생성
}

// input값이 한자리 숫자인 경우 앞에 '0' 붙혀주는 함수
function leftPad(value) {
    if (value < 10) {
        return "0" + value;
    }
    return value;
}

function getScheduleForDate(date) {
    // 이 함수에서는 현재 날짜(date)에 해당하는 일정 정보를 하드코딩하여 리턴합니다.
    // 실제로는 이 부분에서 서버로부터 해당 날짜에 대한 일정 정보를 가져와야 합니다.
    // 임의의 예시로 "일정 1"과 "일정 2"를 리턴하도록 하겠습니다.
    if (date.getFullYear() === 2023 && date.getMonth() === 7 && date.getDate() === 1) {
        return ["일정 1", "일정 2"];
    } else if (date.getFullYear() === 2023 && date.getMonth() === 7 && date.getDate() === 10) {
        return ["일정 3", "일정 4"];
    } else {
        return [];
    }
}



// 날짜 선택 select박스(국)
$(document).ready(function(){
	setDateBox();
});

function setDateBox(){
	var dt = new Date();
	var year = dt.getFullYear();

	$("#year").append("<option value=''>년도</option>");
	for(var y = (year-2); y <= (year+5); y++){
		$("#year").append("<option value='"+ y +"'>"+ y + " 년" +"</option>");
	}
	
	$("#month").append("<option value=''>월</option>");
	for(var i = 1; i <= 12; i++){
		if(String(i).length == '1'){
			$("#month").append("<option value='"+"0" + i +"'>"+"0" + i + "월" +"</option>");
		}else{
			$("#month").append("<option value='"+ i +"'>"+ i + "월" +"</option>");
		}
	}
    
}

// 날짜 선택 select박스(영)
$(document).ready(function(){
	setDateBoxE();
});

function setDateBoxE(){
	var dt = new Date();
	var year = dt.getFullYear();

	$("#yearE").append("<option value=''>Year</option>");
	for(var y = (year-2); y <= (year+5); y++){
		$("#yearE").append("<option value='"+ y +"'>"+ y + "</option>");
	}
	
	$("#monthE").append("<option value=''>Month</option>");
	for(var i = 1; i <= 12; i++){
		if(String(i).length == '1'){
			$("#monthE").append("<option value='"+"0" + i +"'>"+"0" + i + "</option>");
		}else{
			$("#monthE").append("<option value='"+ i +"'>"+ i + "</option>");
		}
	}
    
}



