<template>
	<div class="welcome-bg">
		<div class="welcome">Welcome to PGMD</div>

		<div class="welcome-text">     Animal gut microbiome plays important roles in the health and diseases of animal hosts. In recent years,
		 the number and total volume of animal gut metagenomic data (including both 16S and metagenomic sequencing data) have been increasing rapidly.
		  Here, we present the ADDAGMA (A Database for Domestic Animal Gut Microbiome Atlas) database which hosts all publicly available microbe-phenotype
		   associations data from domestic animal species. Users can browse, search and download data of interest on gut microbiota associated with phenotype.
		       AMDB provides four main functions: 1) Taxa: It shows samples enriched with the bacterial taxon of interest. 2) Samples: It provides the gut
			    microbiota composition of the sample of interest. 3) Projects/Hosts: It gives you summary information for the project or the host.
				 4) Visualization: It visually presents valuable information about the relationships between the samples and the gut microbiota.
		</div>

		<div class="cite">Cite RGI:Yu Z, Chen Y, Zhou Y, Zhang Y, Li M, Ouyang Y, Chebotarov D, Mauleon R, Zhao H, Xie W, McNally K, Wing R,
		 Guo W and Zhang J. Rice Gene Index: A comprehensive pan-genome database for comparative and functional genomics of Asian rice.
		  Molecular Plant, 2023, 16(5):798-801. DOI: 10.1016/j.molp.2023.03.012
		</div>
	</div>
    
	
	<!-- statistics -->
	<div class="statistics-bg">
		<div class="statistics">Statistics</div>
	</div>
	<!-- literatures -->
	<div>
		<img class="literatures-img" src="../images/literatures.png">
	</div>
	<div class="literatures">Literatures</div>
	<div class="literatures-num">217</div>
	<!-- projects -->
	<div>
		<img class="projects-img" src="../images/projects.png">
	</div>
	<div class="projects">Projects</div>
	<div class="projects-num">230</div>
	<!-- runs -->
	<div>
		<img class="runs-img" src="../images/runs.png">
	</div>
	<div class="runs">Runs</div>
	<div class="runs-num">2556</div>
	<!-- sequences -->
	<div>
		<img class="sequences-img" src="../images/sequences.png">
	</div>
	<div class="sequences">Sequences</div>
	<div class="sequences-num">2.4T</div>
	<!-- data-cite -->
	<div class="data-cite">The data was collected before June 23, 2023.</div>
	<!-- latest-news -->
	<div class="latest-news-bg"></div>
	<div class="latest-news">Latest-News</div>
	<!-- news -->
	<ul>
	<div class="news-1"><li>(2023/2/7)Online XXXX module has been developed and available in the XXXXXX and Tools portal.</li> </div>
	<div class="news-2"><li>(2023-07-03) XXXXXXX was cited</li> </div>
	<div class="news-3"><li>(2023-07-03) XXXXXXX was cited</li> </div>
	<div class="news-4"><li>(2023-07-03) XXXXXXX was cited</li> </div>
	</ul>
	<div class="line-1"></div>
	<div class="line-2"></div>
	<!-- frame -->
	<div class="cirle"></div>
	<!-- footer -->
	<div class="footer">
		<div class="footer-info">
			© 2021-2023 XXXX Pig Microbiome @XXXXXXX. All Rights Reserved.<br>
			National Key Laboratory of Agricultural Microbiology<br>
			Frontiers Science Center for Animal Breeding and Sustainable Production<br>
			Huazhong Agricultural University
		</div>
	</div>
</template>

<script>
    export default {
        name:"website",
        setup(){

        }
    }
</script>

<style>

.welcome{
	position: absolute;
    left: 652px;
    top: 48px;
    width: 505px;
    height: 109.63px;
    opacity: 1;
    font-size: 48px;
    font-weight: 700;
    letter-spacing: 0px;
    line-height: 69.5px;
    color: rgba(0, 0, 0, 1);
    text-align: center;
    vertical-align: top;
	z-index: 1;
}

.welcome-bg{
	position: relative;
	left: 36px;
	top: 77px;
	width: 1811px;
	height: 694px;
	
	opacity: 1;
	border-radius: 82px;
	background: rgba(229, 229, 229, 0.3);
}

.welcome-text{
	position: absolute;
	left: 67px;
	top: 158px;
	width: 1636px;
	height: 280.px;
	
	opacity: 1;
	/** 文本1 */
	font-size: 24px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 34.75px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;

}

.cite{
	position: absolute;
	left: 88px;
	top: 505px;
	width: 1636px;
	height: 137px;
	opacity: 1;
	/** 文本1 */
	font-size: 24px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 34.75px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.statistics{
	position: absolute;
	left: 10px;
	top: 7px;
	width: 131px;
	height: 30px;
	opacity: 1;
	/** 文本1 */
	font-size: 20px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 28.96px;
	color: rgba(255, 255, 255, 1);
	text-align: center;
	vertical-align: top;
}

.statistics-bg{
	position: absolute;
	left: 498px;
	top: 917px;
	width: 151.64px;
	height: 48px;
	opacity: 1;
	border-radius: 12px;
	background: rgba(0, 126, 55, 1);
}

.latest-news{
	position: absolute;
	left: 1288px;
	top: 924px;
	width: 131px;
	height: 30px;
	opacity: 1;
	/** 文本1 */
	font-size: 20px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 28.96px;
	color: rgba(255, 255, 255, 1);
	text-align: center;
	vertical-align: top;

}

.latest-news-bg{
	position: absolute;
	left: 1278px;
	top: 917px;
	width: 151.64px;
	height: 48px;
	opacity: 1;
	border-radius: 12px;
	background: rgba(0, 126, 55, 1);

}

.literatures{
	position: absolute;
	left: 415px;
	top: 1001.74px;
	width: 123px;
	height: 28.92px;
	opacity: 1;
	/** 文本1 */
	font-size: 24px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 34.75px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.literatures-num{
	position: absolute;
	left: 437px;
	top: 1045.54px;
	width: 40px;
	height: 28.92px;
	opacity: 1;
	/** 文本1 */
	font-size: 24px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 34.75px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.literatures-img{
	position: absolute;
	left: 297px;
	top: 980.83px;
	width: 128px;
	height: 123.25px;
	opacity: 1;
}

.projects{
	position: absolute;
	left: 726.25px;
	top: 999.26px;
	width: 92px;
	height: 28.92px;
	opacity: 1;
	font-size: 24px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 34.75px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.projects-num{
	position: absolute;
	left: 751.25px;
	top: 1043.06px;
	width: 40px;
	height: 28.92px;
	opacity: 1;
	/** 文本1 */
	font-size: 24px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 34.75px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.projects-img{
	position: absolute;
	left: 593.25px;
	top: 981px;
	width: 133px;
	height: 123.65px;
	opacity: 1;
}

.runs{
	position: absolute;
	left: 428px;
	top: 1161.4px;
	width: 56px;
	height: 28.92px;
	opacity: 1;
	/** 文本1 */
	font-size: 24px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 34.75px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.runs-num{
	position: absolute;
	left: 431px;
	top: 1205.2px;
	width: 54px;
	height: 28.92px;
	opacity: 1;
	/** 文本1 */
	font-size: 24px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 34.75px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.runs-img{
	position: absolute;
	left: 297px;
	top: 1135px;
	width: 128px;
	height: 118.25px;
	opacity: 1;

}

.sequences{
	position: absolute;
	left: 734px;
	top: 1167.4px;
	width: 122px;
	height: 28.92px;
	opacity: 1;
	/** 文本1 */
	font-size: 24px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 34.75px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.sequences-num{
	position: absolute;
	left: 755px;
	top: 1203.76px;
	width: 48px;
	height: 28.92px;
	opacity: 1;
	/** 文本1 */
	font-size: 24px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 34.75px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.sequences-img{
	position: absolute;
	left: 597px;
	top: 1135px;
	width: 128px;
	height: 118.25px;
	opacity: 1;

}

.data-cite{
	position: absolute;
	left: 615px;
	top: 1281px;
	width: 272px;
	height: 20.66px;
	opacity: 1;
	/** 文本1 */
	font-size: 12px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 17.38px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.news-1{
	position: absolute;
	left: 1136px;
	top: 1026px;
	width: 504px;
	height: 48px;
	opacity: 1;
	/** 文本1 */
	font-size: 20px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 22px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.news-2{
	position: absolute;
	left: 1136px;
	top: 1093px;
	width: 504px;
	height: 22px;
	opacity: 1;
	/** 文本1 */
	font-size: 20px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 22px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.news-3{
	position: absolute;
	left: 1136px;
	top: 1161px;
	width: 504px;
	height: 22px;
	opacity: 1;
	/** 文本1 */
	font-size: 20px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 22px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.news-4{
	position: absolute;
	left: 1136px;
	top: 1230px;
	width: 504px;
	height: 22px;
	opacity: 1;
	/** 文本1 */
	font-size: 20px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 22px;
	color: rgba(0, 0, 0, 1);
	text-align: left;
	vertical-align: top;
}

.circle{
	position: absolute;
	left: 1121px;
	top: 1032px;
	width: 6px;
	height: 6px;
	opacity: 1;
	background: rgba(24, 144, 255, 1);
}

.line-1{
	position: absolute;
	left: 1116.8px;
	top: 1033px;
	width: 2px;
	height: 111.68px;
	opacity: 1;
	border-radius: 1.5px;
	background: rgba(0, 0, 0, 0.06);
}

.line-2{
	position: absolute;
	left: 1116.8px;
	top: 1144px;
	width: 2px;
	height: 122.21px;
	opacity: 1;
	border-radius: 1.5px;
	background: rgba(0, 0, 0, 0.06);
}

.footer{
	position: absolute;
	left: 0px;
	top: 1387px;
	width: 1920px;
	height: 155px;
	opacity: 1;
	background: rgba(0, 126, 55, 1);
	box-shadow: 0px 2px 4px  rgba(0, 0, 0, 0.25);
}

.footer-info{
	position: relative;
	left: 438px;
	top: 0px;
	width: 1093px;
	height: 155px;
	opacity: 1;
	/** 文本1 */
	font-size: 24px;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 34.75px;
	color: rgba(255, 255, 255, 1);
	text-align: center;
	vertical-align: top;
}
</style>