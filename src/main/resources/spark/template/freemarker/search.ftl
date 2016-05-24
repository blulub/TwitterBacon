<#assign content>


<style>

  #wrapper {
    width: 800px;
    margin: 100px auto;
  }
  
  #searchbar {
    width: 500px;
    height: 30px;
    font-size: 20px;
    margin-left: 100px;
    margin-right: 10px;
    box-shadow: 0px 0px 0px #9BDCF2;
  }
  
  #search_suggestions {
    display: block;
    position: absolute;
    background-color: #FFFFFF;
    text-align: left;
    font-size: 20px;
    margin-left: 100px;
    width: 500px;
    border-width: 0px 1px 1px 1px;
    border-style: solid;
    border-color: black;
    border-top-style: hidden;
    opacity: 0.4mvn si;
  }
  
  .highlight {
    font-weight: bold;
  }
  
  input[type=text]:focus {
    box-shadow: 0px 0px 5px -2px #333;
  }
  
  input[type=checkbox] {
    -ms-transform: scale(1.5);
    -moz-transform: scale(1.5);
    -webkit-transform: scale(1.5);
    -o-transform: scale(1.5);
    transform: scale(1.5);
  }
  
  [placeholder]:focus::-webkit-input-placeholder {
    transition: opacity 0.5s 0.5s ease; 
    opacity: 0;
  }
  
  .right {
    float:right;
    font-size:12px;
    width: 50px;
    display: table-cell;
    vertical-align: middle;
    text-align: center;
  }
  
  .suggestions:hover {
    background-color: #C9DAF0;
  }
  
  #advanced {
    width: 150px;
    margin:200px auto;
  }
  
  .row {
    clear: both;
    overflow: hidden;
  }
  
  .row label {
    float: left;
    width: 65%;
    text-align: right;
  }
  
  .row input {
    float: right;
    width: 30%;
    text-align:left;
  }
  
</style>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" 
integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" 
integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>


<script src="js/jquery-2.1.1.js"></script>
<script src="js/main.js"></script>

  <div id="wrapper">
   
    <form id="searchBar">
      <input type ="text" id="searchbar" name="searchbar" placeholder="Enter Search..." 
        onkeyup="searchSuggest();" autocomplete="off" />
      <input type="checkbox" id="checkbox" value="true" checked onclick="handleClick();"> Turn Suggestions On 
      <div id="search_suggestions">
      </div>
    </form>
  </div>
  
  <div id="advanced">
    <button type="button" data-toggle="collapse" data-target="#options">
      <span class="glyphicon glyphicon-cog"></span> Advanced Options
    </button> <br> <br>
  
    <div id="options" class="collapse">
      <div class="row">
        <label for="lev">Edit Distance On:</label>
        <input type="checkbox" onclick="updateOptions();" id="lev" ${levChecked}>
      </div>
      <div class="row">
        <label for="levDistance">Enter Max Edit Distance</label>
        <input type="text" onchange="updateOptions();" id="levDistance" value=${levDistance} >
      </div>
      <div class="row">
        <label for="prefix">Prefix On:</label>
        <input type="checkbox" onclick="updateOptions();" id="prefix" ${prefixChecked}>
      </div>
      <div class="row">
        <label for="whitespace">White Space On:</label>
        <input type="checkbox" onclick="updateOptions();" id="whitespace" ${whitespaceChecked}>
      </div>
      <div class="row">
        <label for="smart">Smart Order On:</label>
        <input type="checkbox" onclick="updateOptions();" id="smart" ${smartChecked}>
      </div>
    </div>
  </div
  
  <div id="fileNames">
    <span style="font-weight:bold;"> Words loaded from:</span>
    <br>
    ${fileNames}
  </div>
  
</body>
</#assign>
<#include "main.ftl">