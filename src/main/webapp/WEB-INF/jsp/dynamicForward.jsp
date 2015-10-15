<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index Page</title>
<script type="text/javascript">
	Ext.onReady(function(){
		Ext.create('Ext.panel.Panel', {
		    title: 'Hello',
		    html: '<p>Index page!</p>',
		  //  renderTo: Ext.getBody()
		    renderTo: 'ext'
		});
	});
	
	FusionCharts.ready(function(){
		alert($("chartContainer2").html());
		var revenueChart = new FusionCharts({
			type: "column2d",
			renderAt: "chartContainer",
			width: "500",
			height: "300",
			dataFormat: "json",
			dataSource: {
				"chart": {
				  "caption": "Monthly revenue for last year",
				  "subCaption": "Harry's SuperMart",
				  "xAxisName": "Month",
				  "yAxisName": "Revenues (In USD)",
				  "theme": "zune"
				},
				"data": [
				  {
					 "label": "Jan",
					 "value": "420000"
				  },
				  {
					 "label": "Feb",
					 "value": "810000"
				  },
				  {
					 "label": "Mar",
					 "value": "720000"
				  },
				  {
					 "label": "Apr",
					 "value": "550000"
				  },
				  {
					 "label": "May",
					 "value": "910000"
				  },
				  {
					 "label": "Jun",
					 "value": "510000"
				  },
				  {
					 "label": "Jul",
					 "value": "680000"
				  },
				  {
					 "label": "Aug",
					 "value": "620000"
				  },
				  {
					 "label": "Sep",
					 "value": "610000"
				  },
				  {
					 "label": "Oct",
					 "value": "490000"
				  },
				  {
					 "label": "Nov",
					 "value": "900000"
				  },
				  {
					 "label": "Dec",
					 "value": "730000"
				  }
				]
			}
		});
		revenueChart.render("chartContainer");
	}); 

</script>
</head>

<body>
<div id='ext'></div>
<div id="chartContainer">FusionCharts XT will load here!</div>
<div id="chartContainer2">FusionCharts XT will load here!</div>


</body>
</html>