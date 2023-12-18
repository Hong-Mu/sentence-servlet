<%
    Boolean isLogin = false;
    if(session.getAttribute("user") != null) {
        isLogin = true;
    }

    String authButton = "Login";
    String authButtonLink = "login.jsp";
    if(isLogin) {
        authButtonLink = "logout.jsp";
        authButton = "Logout";
    }

%>

<header class="bg-white shadow">
    <div class="max-w-7xl mx-auto py-6 px-4 sm:px-6 lg:px-8 flex justify-between items-center">
        <h1 class="text-3xl font-bold text-gray-900"><a href="./index.jsp">Sentence</a></h1>
        <div>
            <% if(isLogin) { %>
            <button id="btn-sentence"
                class="btn mx-2 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">New</button>
            <button id="btn-collection"
                class="btn mx-2 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Collection</button>
            <% } %>

            <a href="<%= authButtonLink %>"><button
                    class="btn bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"><%= authButton %> </button></a>
        </div>
    </div>
</header>