# 🎵사용자 감성기반 음악추천 안드로이드 APP "EmoMusic"😍
## 상명대학교 2020 컴퓨터과학과 졸업프로젝트 bakE팀<br>

## What is EmoMusic?
**사용자의 심박수, 현 위치 주변의 날씨 및 시간 그리고 사용자의 감정을 복합적으로 고려한 음악 추천 안드로이드 어플리케이션**

<img width="539" alt="what" src="https://user-images.githubusercontent.com/18053479/101611626-168a7e80-3a4d-11eb-8b2d-6a94185260b5.PNG">

## EmoMusic이 있다면?

<div>
  <img src = https://user-images.githubusercontent.com/18053479/101714136-4765c480-3adc-11eb-9638-eb4a44263bbc.png height="200"/>
  <img src = https://user-images.githubusercontent.com/18053479/101714140-47fe5b00-3adc-11eb-9bca-8f1f345b7f4f.png height="200"/>
</div>
<br>

* 좋아하는 음악을 들으며 스트레스를 해소한다.

* 이 때 듣는 선곡이 감정에 영향을 미친다.

* 이러한 결과를 토대로 사용자의 현재 감정상태에 알맞는 음악을 추천해 사람들게 힐링을 주고자 EmoMusic 개발


## 주요기능
1. 로그인
<img src="https://user-images.githubusercontent.com/18053479/101726049-1fcf2600-3af5-11eb-8e23-3ce4896da3fa.gif" width="200">
2. 노래 추천<br/>
<img src="https://user-images.githubusercontent.com/18053479/101725121-60c63b00-3af3-11eb-85e3-3d2b5098af57.gif" width="200">
4. 플레이어 및 노래 피드백 기능<br/>
<img src="https://user-images.githubusercontent.com/18053479/101726038-1b0a7200-3af5-11eb-851c-127e4a8d17e0.gif" width="200">
5. 플레이리스트 저장<br/>
<img src="https://user-images.githubusercontent.com/18053479/101726053-22318000-3af5-11eb-9288-33ad40211e54.gif" width="200">


## 시스템 구성도

* front-end : Android, kotlin, java

* back-end : node js, type script, firebase
<br/>[backend function repo] - https://github.com/SMU-BakE/backend-function

<img width="600" alt="음악 추천 시스템 구성도" src="https://user-images.githubusercontent.com/18053479/101724256-7e92a080-3af1-11eb-9789-c7fbe75d2706.PNG">

<img width="600" alt="사용자 피드백 구성도" src="https://user-images.githubusercontent.com/18053479/101724497-13959980-3af2-11eb-8646-44e329e2843e.PNG">


## 음악 추천 과정
<img width="400" alt="노래추천1" src="https://user-images.githubusercontent.com/18053479/101717732-39677200-3ae3-11eb-994b-9a4d6a5a7873.PNG">

* 사용자가 추천 요청을 하면 감정, 심박수, 시간, 계절 등을 서버로 보내준다. <br/>
 ```requestSongListWithCondition(conditions)```
 
* 서버에서 해당 함수가 실행되면 노래 추천을 시작한다. 
 
    <img width="400" alt="노래추천" src="https://user-images.githubusercontent.com/18053479/101726829-9c163900-3af6-11eb-925a-8e668cbde604.png">

* 추천된 노래를 사용자에게 보내준다. 

* 사용자가 노래에대한 피드백 점수를 보내면 노래, condition(감정, 심박수, 시간, 계절 등), 만족도 점수를 서버에 보내준다. </br>
```increaseCondition()```

*  서버에서는 db에서 해당 songName에 해당하는 데이터 찾고 점수에 변화를 준다.
``condition[requestCondition.emotion]+=satisfactionCount``<br>
``condition[requestCondition.heartRate]+=satisfactionCount``<br>
``condition[requestCondition.time]+=satisfactionCount``<br>
``condition[requestCondition.weather]+=satisfactionCount``<br>

* 점수들을 표준화 한다. 
```autoNormalizing()```
## User 만족도
![만족도](https://user-images.githubusercontent.com/18053479/101729644-e0f09e80-3afb-11eb-9ac0-30d6f08b48fd.png)

* 88.8%의 사용자가 6점 이상의 점수를 주었다. (10점 만점)
* [만족도 설문 조사 결과 전체] - https://docs.google.com/forms/d/19X5ZMu-tPk_DAc0S2irIy7hsiB-vzrfphgMO3aUQE5I/edit#responses


## 기대효과
<img width="600" alt="기대효과" src="https://user-images.githubusercontent.com/18053479/101729963-8146c300-3afc-11eb-8474-7023ad594811.PNG">

* 노래에 대한 감정 데이터 축적
* 뎅디터 다양화를 통한 정확도 상승과 유저 유입
* 내 상태에 맞는 노래 추천으로 사용자의 감정 위로
* 스트레스 감소 및 우울감 해소 

## 참고자료

[PierfrancescoSoffritti] - https://github.com/PierfrancescoSoffritti/android-youtube-player#youtubeplayerview

## 팀원 
[이해선] https://github.com/HaeSeon

[박은제] https://github.com/prk7048

[박주원] https://github.com/scppliop

[이세연] https://github.com/znoeyes

[이성민] https://github.com/pigzzz8815
