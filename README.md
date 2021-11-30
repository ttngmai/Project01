# Project01
- 사이트 주소: http://3.36.153.102:8080/Project01/main
- 사이트의 회원으로<br/>
아이디 aaaa, bbbb, cccc, dddd를 생성해 두었으며 비밀번호는 모두 1234 입니다.
- AWS의 EC2 서비스를 이용하여 배포하였습니다.
- Spring FrameWork을 사용하여 MVC 패턴으로 만든 게시판입니다.
- 사이트의 전체적인 디자인은 BootStrap4를 활용하였습니다.

## 테이블 구조
![프로젝트 테이블 구조](https://user-images.githubusercontent.com/63570797/100882309-610e7700-34f2-11eb-9a19-344b623cca4c.png)

## 메인 페이지
- 사이트의 처음 페이지입니다.
- 상단에 위치한 네비게이션 바를 이용하여 원하는 페이지로 이동할 수 있습니다.
- 각 게시판의 게시글을 최신순으로 5개씩 보여줍니다.
- "더보기" 버튼을 클릭하면 해당 게시판으로 이동합니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101232704-8e2b7700-36f6-11eb-9904-d9cb70c43421.png"></p>

- 로그인하지 않은 상태에서는 게시글을 볼 수 없습니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101237019-eb382480-3718-11eb-864f-1e8032760e32.gif"></p>

## 회원가입 페이지
- 회원가입을 할 수 있는 페이지입니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101235983-7b25a080-3710-11eb-8837-329beab0daa0.png"></p>

- Form에 잘못된 값을 입력하고 "회원가입" 버튼을 누르면 안내 메세지가 표시됩니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101236000-b45e1080-3710-11eb-9017-42324fd91431.png"></p>

- Form을 모두 작성하고 "회원가입" 버튼을 누르면<br/>
계정이 생성되고 입력한 이메일로 인증메일이 전송됩니다.
- 생성된 계정으로 게시글을 작성하거나 열람하기 위해서는<br/>
전송된 인증메일의 링크를 클릭하여 이메일 인증까지 마쳐야 합니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101236041-0ef76c80-3711-11eb-981d-8463b39ed2fa.png"></p>

## My Page
- 회원정보를 수정할 수 있는 페이지입니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101237986-5fc29180-3720-11eb-87a4-70611c0cbf87.png"></p>

- 프로필 수정 페이지에서는 프로필 사진, 자기소개를 수정할 수 있습니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101238062-06a72d80-3721-11eb-8270-90626c881155.png"></p>

- 비밀번호 변경 페이지에서는 비밀번호를 변경할 수 있습니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101238102-2f2f2780-3721-11eb-8031-ac9fda933a92.png"></p>

- 이메일 변경 페이지에서는 이메일을 변경할 수 있습니다.
- 이메일을 입력하고 "메일 전송" 버튼을 클릭하면 해당 이메일로 인증메일이 전송되며<br/>
전송된 인증메일의 링크를 클릭하면 이메일이 변경됩니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101238119-584fb800-3721-11eb-91ae-da9196c73a85.png"></p>

## 게시판 페이지
- 게시글을 작성, 열람, 검색할 수 있는 페이지입니다.
- 한 페이지에 10개의 게시글을 보여줍니다.
- "초기화" 버튼을 클릭하면 검색 옵션을 적용하지 않은 게시판 첫 페이지로 이동합니다.
- "글쓰기" 버튼을 클릭하면 게시글 작성 페이지로 이동합니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101236434-7531be80-3714-11eb-9c0f-7995301babd4.png"></p>

## 게시글 열람 페이지
- 게시글의 내용을 볼 수 있는 페이지입니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101236637-3a308a80-3716-11eb-91e2-0b8ee04650cd.png"></p>

- 댓글과 댓글에 대한 대댓글을 달 수 있습니다.
- 댓글과 대댓글도 페이징처리를 했으며 5개씩 보여줍니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101236746-f4c08d00-3716-11eb-8e71-5f38da574be9.png"></p>

- 게시글, 댓글, 대댓글에는 추천, 비추천을 주거나 취소할 수 있습니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101237072-4e29bb80-3719-11eb-93f7-a97e1592e0b4.gif"></p>
<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101237153-d019e480-3719-11eb-8f45-e5aba542eb11.gif"></p>

- 게시글, 댓글, 대댓글은 작성자가 수정, 삭제할 수 있습니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101237499-a8784b80-371c-11eb-9294-3f0402465968.gif"></p>
<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101237707-46b8e100-371e-11eb-9761-70fb51a9dc72.gif"></p>

## 게시글 작성 페이지
- 게시글을 작성할 수 있는 페이지입니다.
- 이미지를 첨부할 수 있습니다.

<p align="center"><img src="https://user-images.githubusercontent.com/63570797/101237891-a5328f00-371f-11eb-8f94-404ac109e8fe.png"></p>
