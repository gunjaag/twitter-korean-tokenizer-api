<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>TweetKorean REST API</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="TweetKorean">
        <meta name="author" content="Gunja Agrawal">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="http://getbootstrap.com/assets/css/docs.min.css">
        <link rel="stylesheet" href="http://marcoceppi.github.io/bootstrap-glyphicons/css/bootstrap.icon-large.min.css">
        <style>
            <!-- Overriding styles -->
            body .jumbotron {
                padding: 0px;
            }
            .jumbotron h1 {
                font-size: 40px;
                font-family: "Avant Garde", Avantgarde, "Century Gothic", CenturyGothic, "AppleGothic", sans-serif;
            }
            h2 {
                font-size: 25px;
            }
            h2.featured {
                font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
                font-size: 35px;
            }
        </style>
        <!-- Loaded at top to make sure we track -->
        <script>
            (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
            })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
            
            ga('create', 'UA-47210526-1', 'auto');
            ga('send', 'pageview');
            
        </script>
    </head>
    <body>
        <!-- Header -->
        <div class="jumbotron" style="padding: 0px; margin-bottom: 0px">
            <div class="container">
                <h1>TweetKorean</h1>
                <p>A REST API and web interface for Twitter Korean normalization and tokenization library </p>
            </div>
        </div>
        <div class="container-fluid" style="margin-top: 20px">
            <div class="row">
                <div class="col-md-6">
                    <h5>Request (using v 4.0)</h5>
                    <p>
                        Normalize: 
                        <select id="normalize">
                            <option value="true" selected>true</option>
                            <option value="false">false</option>
                        </select>
                        Stem:      
                        <select id="stem">
                            <option value="true" selected>true</option>
                            <option value="false">false</option>
                        </select>
                    </p>
                    <textarea class="form-control" rows="10" id="textData"></textarea>
                    <br>
                    <button type="button" class="btn btn-success" id="tokenize">Tokenize</button>
                    <hr>
                    <table class="table table-bordered">
                        <tr>
                            <td>Method</td>
                            <td>Response Format</td>
                            <td>API URI</td>
                            <td>Description</td>
                        </tr>
                        <tr>
                            <td>POST</td>
                            <td>JSON</td>
                            <td>/api/tokenize/{normalize}/{stem}<br>Use true/false for {normalize} and {stem}</td>
                            <td>Tokenize Korean text</td>
                        </tr>
                        <tr>
                            <td>POST</td>
                            <td>JSON</td>
                            <td>/api/tokenizeSpeechParts/{normalize}/{stem}<br>Use true/false for {normalize} and {stem}</td>
                            <td>Tokenize Korean into speech parts</td>
                        </tr>
                        <tr>
                            <td>POST</td>
                            <td>JSON</td>
                            <td>/api/tokenizePhrase/{normalize}/{stem}<br>Use true/false for {normalize} and {stem}</td>
                            <td>Tokenize Korean into phrase</td>
                        </tr>
                    </table>
                </div>
                <div class="col-md-5">
                    <h5>Response (using v 4.0)</h5>
                    <p>
                        <!-- empty p for quick hack for spacing -->
                    </p>
                    <textarea class="form-control" rows="7" id="response1" placeholder="Tokenized Korean Text"></textarea>
                    <hr>
                    <textarea class="form-control" rows="7" id="response2" placeholder="Tokenized Korean Text with Speech Parts"></textarea>
                    <hr>
                    <textarea class="form-control" rows="7" id="response3" placeholder="Tokenized Korean Text with Phrases"></textarea>
                </div>
            </div>
            <hr>
            <footer>
                <p>No CopyRight Gunja Agrawal 2015 :)</p>
            </footer>
        </div>
        <!-- /container -->
        <!-- Bootstrap core JavaScript
            ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function(){
              $('#tokenize').on('click',function(){
                    var text = $("#textData").val();
                    var normalize = $("#normalize").val();
                    var stem = $("#stem").val();
            
                    $.ajax({
                        url: '/api/v1/tokenize/' + normalize + '/' + stem,
                        dataType: 'text',
                        type: 'post',
                        contentType: 'text/plain',
                        data: text,
                        success: function( data, textStatus, jQxhr ){
                            $("#response1").val(data);
                        },
                        error: function( jqXhr, textStatus, errorThrown ){
                            console.log( errorThrown );
                        }
                    });
                    $.ajax({
                        url: '/api/v1/tokenizeSpeechParts/' + normalize + '/' + stem,
                        dataType: 'text',
                        type: 'post',
                        contentType: 'text/plain',
                        data: text,
                        success: function( data, textStatus, jQxhr ){
                            $("#response2").val(data);
                        },
                        error: function( jqXhr, textStatus, errorThrown ){
                            console.log( errorThrown );
                        }
                    });
                    $.ajax({
                        url: '/api/v1/tokenizePhrase/' + normalize + '/' + stem,
                        dataType: 'text',
                        type: 'post',
                        contentType: 'text/plain',
                        data: text,
                        success: function( data, textStatus, jQxhr ){
                            $("#response3").val(data);
                        },
                        error: function( jqXhr, textStatus, errorThrown ){
                            console.log( errorThrown );
                        }
                    });
              });
            });
        </script>
    </body>
</html>