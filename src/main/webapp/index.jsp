<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.hongmu.sentence.dao.SentenceDao" %>
<%@ page import="com.hongmu.sentence.dao.CollectionDao" %>
<%@ page import="com.hongmu.sentence.model.Sentence" %>
<%@ page import="com.hongmu.sentence.model.Collection" %>

<%@ page import="java.util.List" %>
<%
    SentenceDao dao = new SentenceDao();
    List<Sentence> list = dao.getAll();
    int max = 5;
    if(list.size() < max) {
        max = list.size();
    }

    CollectionDao collectionDao = new CollectionDao();
    List<Collection> collectionList = collectionDao.getAll();
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sentence</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <style>
        .card {
            background-color: #ffffff;
            border: 1px solid #e5e7eb;
            border-radius: 0.5rem;
        }

        .card-header {
            background-color: #f9fafb;
            border-bottom: 1px solid #e5e7eb;
            padding: 0.75rem 1rem;
            font-weight: 700;
        }

        .card-body {
            padding: 1rem;
        }

        .card-footer {
            border-top: 1px solid #e5e7eb;
            padding: 0.75rem 1rem;
            text-align: right;
        }
    </style>
    <script>
        window.onload = function () {
            const sentenceBackground = document.querySelector('#form-sentence-background');
            const sentence = document.querySelector('#btn-sentence');

            sentence.addEventListener('click', function () {
                sentenceBackground.classList.remove('hidden');
            });

            sentenceBackground.addEventListener('click', function (event) {
                if(event.target === sentenceBackground) {
                    sentenceBackground.classList.add('hidden');
                }
            });

            const collectionBackground = document.querySelector('#form-collection-background');
            const collection = document.querySelector('#btn-collection');
            
            collection.addEventListener('click', function () {
                collectionBackground.classList.remove('hidden');
            });

            collectionBackground.addEventListener('click', function (event) {
                if(event.target === collectionBackground) {
                    collectionBackground.classList.add('hidden');
                }
            });


            $('#btn-random').click(function () {
                console.log('click');
                $.ajax({
                    type: 'POST',
                    url: '/Sentence/random',
                    success: function (response) {
                        $('#random-title').text(response.title);
                        $('#random-subtitle').text(response.subtitle);
                    }
                });
            });

        }
    </script>
</head>

<body class="bg-gray-100">

    <!-- header -->
    <%@ include file="/include/header.jsp" %>


        <main class="my-10">
            <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">

                <!-- banner -->
                <div class="flex justify-between items-center mb-6 h-96">

                    <!-- random iamge -->
                    <div class="w-1/3">
                        <img src="https://source.unsplash.com/random/400x300" alt="Random Image"
                            class="rounded-lg shadow-lg ">
                    </div>

                    <!-- random sentence -->
                    <div class="w-2/3 text-center">
                        <p id="random-title" class="text-lg font-semibold mb-4"><%= list.get(0).getTitle() %></p>
                        <p id="random-subtitle" class="text-sm mb-16"><%= list.get(0).getSubtitle() %></p>
                        <button id="btn-random"
                            class="btn bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Random</button>
                    </div>
                </div>

                <!-- grid card -->
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">

                    <div class="card">
                        <div class="card-header">
                            Today's Sentence
                        </div>
                        <div class="card-body">
                            <% for(int i = 0; i < max; i++) { %>
                            <p><%= list.get(i).getTitle() %></p>
                            <% } %>
                        </div>
                        <div class="card-footer">
                            <button class="btn">More</button>
                        </div>
                    </div>

                    <% for(int i = 0; i < collectionList.size(); i++) { %>
                    <div class="card">
                        <div class="card-header">
                            <%= collectionList.get(i).getTitle() %>
                        </div>
                        <div class="card-body">
                            <% 
                                List<Sentence> tempList = dao.getByCollectionId(collectionList.get(i).getId());
                                int tMax = 5;
                                if(tempList.size() < tMax) {
                                    tMax = tempList.size();
                                }
                                for(int j = 0; j < tMax; j++) { 
                            %>
                            <p><%= tempList.get(j).getTitle() %></p>
                            <% } %>
                        </div>
                        <div class="card-footer">
                            <a href="/Sentence/collection.jsp?id=<%= collectionList.get(i).getId() %>">
                                <button class="btn">More</button>
                            </a>
                        </div>
                    </div>
                    <% } %>
                </div>

                <!-- New -->
                <div id="form-sentence-background"
                    class="hidden absolute top-0 left-0 w-screen bg-gray-100 bg-opacity-50 h-screen flex items-center justify-center">
                    <div id="form-sentence" 
                        class="bg-white z-10 p-8 rounded-lg shadow-lg max-w-md w-full">
                        <h1 class="text-2xl font-bold mb-8"><a href="index.jsp">New</a></h1>

                        <form method="post" action="/Sentence/sentence">

                            <div class="mb-4">
                                <label for="sentence" class="block text-gray-700 text-sm font-bold mb-2">Sentence</label>
                                <input type="text" id="sentence" name="sentence" required
                                    class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                            </div>

                            <div class="mb-6">
                                <label for="author"
                                    class="block text-gray-700 text-sm font-bold mb-2">Author</label>
                                <input type="text" id="author" name="author" required
                                    class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline">
                            </div>

                            <div class="flex items-center justify-between">
                                <button type="submit"
                                    class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                                    등록
                                </button>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- Collection -->
                <div id="form-collection-background"
                    class="hidden absolute top-0 left-0 w-screen bg-gray-100 bg-opacity-50 h-screen flex items-center justify-center">
                    <div id="form-collection" 
                        class="bg-white z-10 p-8 rounded-lg shadow-lg max-w-md w-full">
                        <h1 class="text-2xl font-bold mb-8"><a href="index.jsp">Collection</a></h1>

                        <form method="post" action="/Sentence/collection">

                            <div class="mb-4">
                                <label for="name" class="block text-gray-700 text-sm font-bold mb-2">Name</label>
                                <input type="text" id="name" name="name" required
                                    class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                            </div>

                            <div class="flex items-center justify-between">
                                <button type="submit"
                                    class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                                    생성
                                </button>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </main>
</body>

</html>