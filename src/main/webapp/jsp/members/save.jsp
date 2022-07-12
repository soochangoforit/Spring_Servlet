<%@ page import="Spring.servlet.domain.member.MemberRepository" %>
<%@ page import="Spring.servlet.domain.member.Member" %>
<%@ page import="Spring.servlet.domain.member.MemberRepository" %>
<%@ page import="Spring.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // request, response 사용 가능 , jsp도 나중에 servlet으로 java코드가 변환되어서 사용 가능하다. servlet class의 service로직을 그대로 사용할 수 있다.
  MemberRepository memberRepository = MemberRepository.getInstance();
  System.out.println("save.jsp");

  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));

  Member member = new Member(username, age);
  System.out.println("member = " + member);
  memberRepository.save(member);

%>
<html>
<head>
  <meta charset="UTF-8">
</head>
<body>
    성공
    <ul>
      <li>id=<%=member.getId()%></li>
      <li>username=<%=member.getUsername()%></li>
      <li>age=<%=member.getAge()%></li>
    </ul>
    <a href="/index.html">메인</a>
</body>
</html>
