$(document).ready(function(){

$('#report').hide();
  function executeQuery() {
    var query = $('#queryStatus').text();
    if(query!="stop"){
      $.ajax({
        url: 'http://localhost:9998/analytics',
        type: 'GET',
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Headers':'Content-Type'
        },
        data:{"query":query},
        success: function(data) {
          var dataPosCount= data.posCount;
          var dataNegCount = data.negCount;
          var dataAllCount =dataPosCount+dataNegCount;
          posCount=dataPosCount;
          negCount=dataNegCount;
          allCount=dataAllCount;
          if(allCount!=0){
            var pp = (posCount/allCount)*100;
            var np = (negCount/allCount)*100;
            var trace1 = {
              x: [pp.toFixed(2)],
              y: ['positive %'],
              name: 'Positive Sentiment',
              orientation: 'h',
              marker: {
                color: 'rgba(0,153,51,0.6)',
                width: 1
              },
              type: 'bar'
            };

            var trace2 = {
              x: [np.toFixed(2)],
              y: ['negative %'],
              name: 'Negative Sentiment',
              orientation: 'h',
              type: 'bar',
              marker: {
                color: 'rgba(255,0,0,0.6)',
                width: 1
              }
            };

            var dataforchart = [trace1, trace2];

            var layout = {
              title: 'Sentiment',
              barmode: 'stack'
            };

            Plotly.newPlot('sentiment', dataforchart, layout);

            var entities = data.entities;
            var poskeywords = data.poskeywords;
            var negkeywords = data.negkeywords;
            var stories = data.stories;
            var poswords=[];
            var negwords=[];
            var entitywords=[];
            var storiesList=[];
            var storiesCloud=[];

            for(var i=0;i<poskeywords.length;++i){
              var poskeyword = poskeywords[i];
              for(key in poskeyword){
                poswords.push({'text':key,'weight':poskeyword[key]});
              }
            }

            for(var i=0;i<negkeywords.length;++i){
              var negkeyword = negkeywords[i];
              for(key in negkeyword){
                negwords.push({'text':key,'weight':negkeyword[key]});
              }
            }

            for(var i=0;i<entities.length;++i){
              var entity = entities[i];
              for(key in entity){
                entitywords.push({'text':key,'weight':entity[key]});
              }
            }

            for(var i=0;i<stories.length;++i){
              var story = stories[i];
              for(key in story){
                var randomWeight=Math.floor(Math.random() * (5 - 3 + 1)) + 3;
                storiesList.push({'text':key,'weight':randomWeight});
              }
            }

            $('#positivekeywords').jQCloud('update',poswords);
            $('#negativekeywords').jQCloud('update',negwords);
            $('#entities').jQCloud('update',entitywords);
            $('#stories-list').jQCloud('update',storiesList);

          }
        }
      });
      setTimeout(executeQuery, 15000); // you could choose not to continue on failure...
    }
  }


  function runAnalysis(){
    var query = $('#queryStatus').text();
    if(query!="stop"){
      setTimeout(executeQuery, 15000);
    }
    else{
      $('#sentiment').hide()
      $('#queryText').text("");
      $('#entities').jQCloud('destroy');
      $('#entities').hide();
      $('#report').hide();
      $('#positivekeywords').jQCloud('destroy');
      $('#positivekeywords').hide();
      $('#negativekeywords').jQCloud('destroy');
      $('#negativekeywords').hide();
      $('#stories-list').jQCloud('destroy');
      $('#stories-list').hide();
      location.reload();
    }
  }

  $('#submitQueryButton').click(function(){
    var query = $('#queryText').val();
    $('#report').show();
    $.ajax({
      url:"http://localhost:9998/read",
      type: 'GET',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers':'Content-Type'
      },
      data:{"query":query},
      success:function(data){
        if(data=="200"){
          $("#queryStatus").text(query);



          $("#entities").jQCloud([],{
            width:400,
            height:300,
            colors:["#030E52","#6771AE","#0A1F9F","#0F1E7D","#04496E","#043550","#004B4B","#006868","#04476E"],
            shape:'rectangular'
          });

          $("#stories-list").jQCloud([],{
            width:850,
            height:450,
            colors:["#370776","#280755","#793FC6","#2E0864","#3C0885","#440899","#4A2281","#531F7F","#3E056F"],
            shape:'rectangular'
          });

          $("#positivekeywords").jQCloud([],{
            width:400,
            height:300,
            colors: ["#4AF900", "#3FD200", "#04170D", "#00F371", "#68FA00", "#468A15", "#366019", "#2C6900", "#1B4100"]
          });

          $("#negativekeywords").jQCloud([],{
            width:400,
            height:300,
            colors: ["#520F0F", "#7E2020", "#98100C", "#870300", "#AA3C39", "#C10500", "#580200", "#E50600", "#A81502"]
          });
          runAnalysis();
        }
      },
      error:function(err){
      }
    })
  });

  $('#stopButton').click(function(){

    $.ajax({
      url:"http://localhost:9998/read",
      type: 'GET',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers':'Content-Type'
      },
      data:{"query":"stop"},
      success:function(data){
        if(data=="200"){
          $("#queryStatus").text("stop");
          $("#report").hide();
          runAnalysis();
          location.reload();
        }
      },
      error:function(err){
      }
    })
  });

});
