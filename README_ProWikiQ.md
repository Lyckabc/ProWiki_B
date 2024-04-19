<html><head><meta http-equiv="Content-Type" content="text/html; charset=utf-8"/><title>README_ProWikiQ</title><style>
/* cspell:disable-file */
/* webkit printing magic: print all background colors */
html {
	-webkit-print-color-adjust: exact;
}
* {
	box-sizing: border-box;
	-webkit-print-color-adjust: exact;
}

html,
body {
	margin: 0;
	padding: 0;
}
@media only screen {
	body {
		margin: 2em auto;
		max-width: 900px;
		color: rgb(55, 53, 47);
	}
}

body {
	line-height: 1.5;
	white-space: pre-wrap;
}

a,
a.visited {
	color: inherit;
	text-decoration: underline;
}

.pdf-relative-link-path {
	font-size: 80%;
	color: #444;
}

h1,
h2,
h3 {
	letter-spacing: -0.01em;
	line-height: 1.2;
	font-weight: 600;
	margin-bottom: 0;
}

.page-title {
	font-size: 2.5rem;
	font-weight: 700;
	margin-top: 0;
	margin-bottom: 0.75em;
}

h1 {
	font-size: 1.875rem;
	margin-top: 1.875rem;
}

h2 {
	font-size: 1.5rem;
	margin-top: 1.5rem;
}

h3 {
	font-size: 1.25rem;
	margin-top: 1.25rem;
}

.source {
	border: 1px solid #ddd;
	border-radius: 3px;
	padding: 1.5em;
	word-break: break-all;
}

.callout {
	border-radius: 3px;
	padding: 1rem;
}

figure {
	margin: 1.25em 0;
	page-break-inside: avoid;
}

figcaption {
	opacity: 0.5;
	font-size: 85%;
	margin-top: 0.5em;
}

mark {
	background-color: transparent;
}

.indented {
	padding-left: 1.5em;
}

hr {
	background: transparent;
	display: block;
	width: 100%;
	height: 1px;
	visibility: visible;
	border: none;
	border-bottom: 1px solid rgba(55, 53, 47, 0.09);
}

img {
	max-width: 100%;
}

@media only print {
	img {
		max-height: 100vh;
		object-fit: contain;
	}
}

@page {
	margin: 1in;
}

.collection-content {
	font-size: 0.875rem;
}

.column-list {
	display: flex;
	justify-content: space-between;
}

.column {
	padding: 0 1em;
}

.column:first-child {
	padding-left: 0;
}

.column:last-child {
	padding-right: 0;
}

.table_of_contents-item {
	display: block;
	font-size: 0.875rem;
	line-height: 1.3;
	padding: 0.125rem;
}

.table_of_contents-indent-1 {
	margin-left: 1.5rem;
}

.table_of_contents-indent-2 {
	margin-left: 3rem;
}

.table_of_contents-indent-3 {
	margin-left: 4.5rem;
}

.table_of_contents-link {
	text-decoration: none;
	opacity: 0.7;
	border-bottom: 1px solid rgba(55, 53, 47, 0.18);
}

table,
th,
td {
	border: 1px solid rgba(55, 53, 47, 0.09);
	border-collapse: collapse;
}

table {
	border-left: none;
	border-right: none;
}

th,
td {
	font-weight: normal;
	padding: 0.25em 0.5em;
	line-height: 1.5;
	min-height: 1.5em;
	text-align: left;
}

th {
	color: rgba(55, 53, 47, 0.6);
}

ol,
ul {
	margin: 0;
	margin-block-start: 0.6em;
	margin-block-end: 0.6em;
}

li > ol:first-child,
li > ul:first-child {
	margin-block-start: 0.6em;
}

ul > li {
	list-style: disc;
}

ul.to-do-list {
	padding-inline-start: 0;
}

ul.to-do-list > li {
	list-style: none;
}

.to-do-children-checked {
	text-decoration: line-through;
	opacity: 0.375;
}

ul.toggle > li {
	list-style: none;
}

ul {
	padding-inline-start: 1.7em;
}

ul > li {
	padding-left: 0.1em;
}

ol {
	padding-inline-start: 1.6em;
}

ol > li {
	padding-left: 0.2em;
}

.mono ol {
	padding-inline-start: 2em;
}

.mono ol > li {
	text-indent: -0.4em;
}

.toggle {
	padding-inline-start: 0em;
	list-style-type: none;
}

/* Indent toggle children */
.toggle > li > details {
	padding-left: 1.7em;
}

.toggle > li > details > summary {
	margin-left: -1.1em;
}

.selected-value {
	display: inline-block;
	padding: 0 0.5em;
	background: rgba(206, 205, 202, 0.5);
	border-radius: 3px;
	margin-right: 0.5em;
	margin-top: 0.3em;
	margin-bottom: 0.3em;
	white-space: nowrap;
}

.collection-title {
	display: inline-block;
	margin-right: 1em;
}

.page-description {
    margin-bottom: 2em;
}

.simple-table {
	margin-top: 1em;
	font-size: 0.875rem;
	empty-cells: show;
}
.simple-table td {
	height: 29px;
	min-width: 120px;
}

.simple-table th {
	height: 29px;
	min-width: 120px;
}

.simple-table-header-color {
	background: rgb(247, 246, 243);
	color: black;
}
.simple-table-header {
	font-weight: 500;
}

time {
	opacity: 0.5;
}

.icon {
	display: inline-block;
	max-width: 1.2em;
	max-height: 1.2em;
	text-decoration: none;
	vertical-align: text-bottom;
	margin-right: 0.5em;
}

img.icon {
	border-radius: 3px;
}

.user-icon {
	width: 1.5em;
	height: 1.5em;
	border-radius: 100%;
	margin-right: 0.5rem;
}

.user-icon-inner {
	font-size: 0.8em;
}

.text-icon {
	border: 1px solid #000;
	text-align: center;
}

.page-cover-image {
	display: block;
	object-fit: cover;
	width: 100%;
	max-height: 30vh;
}

.page-header-icon {
	font-size: 3rem;
	margin-bottom: 1rem;
}

.page-header-icon-with-cover {
	margin-top: -0.72em;
	margin-left: 0.07em;
}

.page-header-icon img {
	border-radius: 3px;
}

.link-to-page {
	margin: 1em 0;
	padding: 0;
	border: none;
	font-weight: 500;
}

p > .user {
	opacity: 0.5;
}

td > .user,
td > time {
	white-space: nowrap;
}

input[type="checkbox"] {
	transform: scale(1.5);
	margin-right: 0.6em;
	vertical-align: middle;
}

p {
	margin-top: 0.5em;
	margin-bottom: 0.5em;
}

.image {
	border: none;
	margin: 1.5em 0;
	padding: 0;
	border-radius: 0;
	text-align: center;
}

.code,
code {
	background: rgba(135, 131, 120, 0.15);
	border-radius: 3px;
	padding: 0.2em 0.4em;
	border-radius: 3px;
	font-size: 85%;
	tab-size: 2;
}

code {
	color: #eb5757;
}

.code {
	padding: 1.5em 1em;
}

.code-wrap {
	white-space: pre-wrap;
	word-break: break-all;
}

.code > code {
	background: none;
	padding: 0;
	font-size: 100%;
	color: inherit;
}

blockquote {
	font-size: 1.25em;
	margin: 1em 0;
	padding-left: 1em;
	border-left: 3px solid rgb(55, 53, 47);
}

.bookmark {
	text-decoration: none;
	max-height: 8em;
	padding: 0;
	display: flex;
	width: 100%;
	align-items: stretch;
}

.bookmark-title {
	font-size: 0.85em;
	overflow: hidden;
	text-overflow: ellipsis;
	height: 1.75em;
	white-space: nowrap;
}

.bookmark-text {
	display: flex;
	flex-direction: column;
}

.bookmark-info {
	flex: 4 1 180px;
	padding: 12px 14px 14px;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.bookmark-image {
	width: 33%;
	flex: 1 1 180px;
	display: block;
	position: relative;
	object-fit: cover;
	border-radius: 1px;
}

.bookmark-description {
	color: rgba(55, 53, 47, 0.6);
	font-size: 0.75em;
	overflow: hidden;
	max-height: 4.5em;
	word-break: break-word;
}

.bookmark-href {
	font-size: 0.75em;
	margin-top: 0.25em;
}

.sans { font-family: ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol"; }
.code { font-family: "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace; }
.serif { font-family: Lyon-Text, Georgia, ui-serif, serif; }
.mono { font-family: iawriter-mono, Nitti, Menlo, Courier, monospace; }
.pdf .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK JP'; }
.pdf:lang(zh-CN) .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK SC'; }
.pdf:lang(zh-TW) .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK TC'; }
.pdf:lang(ko-KR) .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK KR'; }
.pdf .code { font-family: Source Code Pro, "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK JP'; }
.pdf:lang(zh-CN) .code { font-family: Source Code Pro, "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK SC'; }
.pdf:lang(zh-TW) .code { font-family: Source Code Pro, "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK TC'; }
.pdf:lang(ko-KR) .code { font-family: Source Code Pro, "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK KR'; }
.pdf .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK JP'; }
.pdf:lang(zh-CN) .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK SC'; }
.pdf:lang(zh-TW) .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK TC'; }
.pdf:lang(ko-KR) .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK KR'; }
.pdf .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK JP'; }
.pdf:lang(zh-CN) .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK SC'; }
.pdf:lang(zh-TW) .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK TC'; }
.pdf:lang(ko-KR) .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK KR'; }
.highlight-default {
	color: rgba(55, 53, 47, 1);
}
.highlight-gray {
	color: rgba(120, 119, 116, 1);
	fill: rgba(120, 119, 116, 1);
}
.highlight-brown {
	color: rgba(159, 107, 83, 1);
	fill: rgba(159, 107, 83, 1);
}
.highlight-orange {
	color: rgba(217, 115, 13, 1);
	fill: rgba(217, 115, 13, 1);
}
.highlight-yellow {
	color: rgba(203, 145, 47, 1);
	fill: rgba(203, 145, 47, 1);
}
.highlight-teal {
	color: rgba(68, 131, 97, 1);
	fill: rgba(68, 131, 97, 1);
}
.highlight-blue {
	color: rgba(51, 126, 169, 1);
	fill: rgba(51, 126, 169, 1);
}
.highlight-purple {
	color: rgba(144, 101, 176, 1);
	fill: rgba(144, 101, 176, 1);
}
.highlight-pink {
	color: rgba(193, 76, 138, 1);
	fill: rgba(193, 76, 138, 1);
}
.highlight-red {
	color: rgba(212, 76, 71, 1);
	fill: rgba(212, 76, 71, 1);
}
.highlight-gray_background {
	background: rgba(241, 241, 239, 1);
}
.highlight-brown_background {
	background: rgba(244, 238, 238, 1);
}
.highlight-orange_background {
	background: rgba(251, 236, 221, 1);
}
.highlight-yellow_background {
	background: rgba(251, 243, 219, 1);
}
.highlight-teal_background {
	background: rgba(237, 243, 236, 1);
}
.highlight-blue_background {
	background: rgba(231, 243, 248, 1);
}
.highlight-purple_background {
	background: rgba(244, 240, 247, 0.8);
}
.highlight-pink_background {
	background: rgba(249, 238, 243, 0.8);
}
.highlight-red_background {
	background: rgba(253, 235, 236, 1);
}
.block-color-default {
	color: inherit;
	fill: inherit;
}
.block-color-gray {
	color: rgba(120, 119, 116, 1);
	fill: rgba(120, 119, 116, 1);
}
.block-color-brown {
	color: rgba(159, 107, 83, 1);
	fill: rgba(159, 107, 83, 1);
}
.block-color-orange {
	color: rgba(217, 115, 13, 1);
	fill: rgba(217, 115, 13, 1);
}
.block-color-yellow {
	color: rgba(203, 145, 47, 1);
	fill: rgba(203, 145, 47, 1);
}
.block-color-teal {
	color: rgba(68, 131, 97, 1);
	fill: rgba(68, 131, 97, 1);
}
.block-color-blue {
	color: rgba(51, 126, 169, 1);
	fill: rgba(51, 126, 169, 1);
}
.block-color-purple {
	color: rgba(144, 101, 176, 1);
	fill: rgba(144, 101, 176, 1);
}
.block-color-pink {
	color: rgba(193, 76, 138, 1);
	fill: rgba(193, 76, 138, 1);
}
.block-color-red {
	color: rgba(212, 76, 71, 1);
	fill: rgba(212, 76, 71, 1);
}
.block-color-gray_background {
	background: rgba(241, 241, 239, 1);
}
.block-color-brown_background {
	background: rgba(244, 238, 238, 1);
}
.block-color-orange_background {
	background: rgba(251, 236, 221, 1);
}
.block-color-yellow_background {
	background: rgba(251, 243, 219, 1);
}
.block-color-teal_background {
	background: rgba(237, 243, 236, 1);
}
.block-color-blue_background {
	background: rgba(231, 243, 248, 1);
}
.block-color-purple_background {
	background: rgba(244, 240, 247, 0.8);
}
.block-color-pink_background {
	background: rgba(249, 238, 243, 0.8);
}
.block-color-red_background {
	background: rgba(253, 235, 236, 1);
}
.select-value-color-uiBlue { background-color: rgba(35, 131, 226, .07); }
.select-value-color-pink { background-color: rgba(245, 224, 233, 1); }
.select-value-color-purple { background-color: rgba(232, 222, 238, 1); }
.select-value-color-green { background-color: rgba(219, 237, 219, 1); }
.select-value-color-gray { background-color: rgba(227, 226, 224, 1); }
.select-value-color-transparentGray { background-color: rgba(227, 226, 224, 0); }
.select-value-color-translucentGray { background-color: rgba(255, 255, 255, 0.0375); }
.select-value-color-orange { background-color: rgba(250, 222, 201, 1); }
.select-value-color-brown { background-color: rgba(238, 224, 218, 1); }
.select-value-color-red { background-color: rgba(255, 226, 221, 1); }
.select-value-color-yellow { background-color: rgba(253, 236, 200, 1); }
.select-value-color-blue { background-color: rgba(211, 229, 239, 1); }
.select-value-color-pageGlass { background-color: undefined; }
.select-value-color-washGlass { background-color: undefined; }

.checkbox {
	display: inline-flex;
	vertical-align: text-bottom;
	width: 16;
	height: 16;
	background-size: 16px;
	margin-left: 2px;
	margin-right: 5px;
}

.checkbox-on {
	background-image: url("data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%3E%0A%3Crect%20width%3D%2216%22%20height%3D%2216%22%20fill%3D%22%2358A9D7%22%2F%3E%0A%3Cpath%20d%3D%22M6.71429%2012.2852L14%204.9995L12.7143%203.71436L6.71429%209.71378L3.28571%206.2831L2%207.57092L6.71429%2012.2852Z%22%20fill%3D%22white%22%2F%3E%0A%3C%2Fsvg%3E");
}

.checkbox-off {
	background-image: url("data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%3E%0A%3Crect%20x%3D%220.75%22%20y%3D%220.75%22%20width%3D%2214.5%22%20height%3D%2214.5%22%20fill%3D%22white%22%20stroke%3D%22%2336352F%22%20stroke-width%3D%221.5%22%2F%3E%0A%3C%2Fsvg%3E");
}
	
</style></head><body><article id="fbf6c549-ae73-4f57-a4fe-8aa3cbc67e8f" class="page sans"><header><h1 class="page-title">README_ProWikiQ</h1><p class="page-description"></p></header><div class="page-body"><div><details open=""><summary style="font-weight:600;font-size:1.875em;line-height:1.3;margin:0">개요</summary><div class="indented"><p id="d22395d5-54e9-4484-a8a0-685a56304910" class="">Province Wiki Q- Encyclopedia </p><p id="0efb4ebf-094a-45ac-93d7-e07788c6becc" class="">지방관리들의 빠른 지령 백과사전</p><details open=""><summary style="font-weight:600;font-size:1.5em;line-height:1.3;margin:0">개발환경</summary><div class="indented"><p id="af28db63-3460-48b7-9f73-ebe9fc236ac9" class="">OS : Mac OS</p><p id="f451e421-1af3-446e-84fd-6f3bf501f5cd" class="">IDE : IntelliJ</p><p id="db7e5626-365c-4b45-86e0-9aa873f9643b" class="">JAVA Version : JDK 11</p><p id="0f1a20ba-0394-4d6c-9426-b45a1df19eec" class="">SpringBoot Version : 2.6.16-SNAPSHOT</p><p id="5afb0a2c-01a1-4aad-a1b7-33dea6b49ef1" class="">DataBase : PostgreSQL</p><ul id="792e4934-bc94-40f7-9244-49ecf4ce03e9" class="toggle"><li><details open=""><summary>Build Tool : Gradle 7.3</summary><p id="73b174a7-8a8a-43e6-b435-4d4f75b91831" class="">//  <br/>// for Android minimum 7.3 though <br/></p></details></li></ul><ul id="89979988-a1c5-47bb-81c3-5d18d1cc3c24" class="toggle"><li><details open=""><summary>Gradle DSL : Kotlin</summary><p id="9cdbad01-063a-4ec5-adba-1b46a57dda8c" class="">//  </p></details></li></ul><p id="d6044eb3-9555-4e59-8448-3575f5d0a094" class="">Manage Tool : GitHub</p><p id="ad687dcf-01ed-497e-a59a-264490ab5c6c" class="">Git Client : Fork</p><ul id="9678909b-9584-4a2b-95b4-edc593530e3c" class="toggle"><li><details open=""><summary>API tool : Postman</summary></details></li></ul></div></details><details open=""><summary style="font-weight:600;font-size:1.5em;line-height:1.3;margin:0">Dependencies</summary><div class="indented"><ul id="759d282a-251b-4df2-b2bc-6754f486b338" class="toggle"><li><details open=""><summary>Spring Data JPA</summary></details></li></ul><ul id="5731938b-545d-4204-b14b-802fbcd82f67" class="toggle"><li><details open=""><summary>Spring Security</summary></details></li></ul><ul id="48abd98f-b02d-4103-b171-6937d0b2980a" class="toggle"><li><details open=""><summary>Swagger</summary></details></li></ul><ul id="867ed3f0-5073-4464-8cda-367cf7e414f1" class="toggle"><li><details open=""><summary>Lombok</summary></details></li></ul><ul id="ae0dca56-57c0-48ce-960f-eb9333f445c6" class="toggle"><li><details open=""><summary>Spring Web</summary></details></li></ul></div></details><details open=""><summary style="font-weight:600;font-size:1.5em;line-height:1.3;margin:0">기술 스택</summary><div class="indented"><ul id="c451f2bf-fbd7-4aa9-8b47-dab80c9ca4c7" class="toggle"><li><details open=""><summary>Backend</summary><ul id="2147e6c1-34e1-45ab-9575-12c78193842f" class="toggle"><li><details open=""><summary>Framework : Spring Boot 2.6.16-SNAPSHOT</summary><p id="75b35196-d04d-4139-b500-de95eac418ad" class="">// <a href="https://www.notion.so/JAVA-Spring-4e112a8c53304c248e9a0af3570fea8e?pvs=21">Spring docs</a> 참고 (제일 최신 2.6.x 변동x 지원 끝) <a href="https://www.notion.so/JAVA-Spring-4e112a8c53304c248e9a0af3570fea8e?pvs=21">2.7.x는 </a> 2025-08-24 까지 변동예정.</p></details></li></ul><ul id="05effb73-3f52-4148-b69c-602eafa639d8" class="toggle"><li><details open=""><summary>Spring Data JPA</summary><p id="00832932-cc7b-4975-b729-a9a2c5dab51a" class="">
</p></details></li></ul></details></li></ul><ul id="aab0b2f7-2431-42b1-be02-389380746070" class="toggle"><li><details open=""><summary>DB</summary><p id="274e3690-d340-4990-b8a6-82facab07fe9" class="">PostgreSQL</p><p id="f018c440-9ce8-4058-800f-6c16d8d5f018" class="">// </p><p id="e35e70c2-5a2a-40f2-a954-0994529c4df8" class="">(Option) </p></details></li></ul><ul id="d77a3ab7-a609-4cef-af2a-b228f90b519e" class="toggle"><li><details open=""><summary>(Option)Frontend</summary></details></li></ul></div></details><details open=""><summary style="font-weight:600;font-size:1.5em;line-height:1.3;margin:0">기능</summary><div class="indented"><ul id="4aa35585-37c8-4ce0-b36b-e48c7497fa20" class="toggle"><li><details open=""><summary>계정</summary><ul id="471d88a1-141f-4e8d-8692-97374790486b" class="toggle"><li><details open=""><summary>회원가입</summary><p id="6556d9f0-c85d-4b35-9b14-7d0241191bfb" class=""> </p></details></li></ul><ul id="ed26516b-f8cf-44e5-a7e0-fa231a8367f6" class="toggle"><li><details open=""><summary>로그인</summary><p id="cd0c0de8-a431-4328-abcd-a836eeee7f5e" class=""> </p><p id="6c6a19e8-62fd-4d4f-95d4-d9138c8a9c19" class=""> </p></details></li></ul><ul id="74b124d9-da1f-44b1-b269-df68930ae266" class="toggle"><li><details open=""><summary>권한부여</summary><p id="5a6dbd56-1560-4480-b41b-2e808806a2dc" class=""> </p></details></li></ul><ul id="026b6c23-7880-4e12-9cb1-1128ff9dd2ee" class="toggle"><li><details open=""><summary>조회</summary><p id="38a8e6c1-e3be-40be-82ff-5d004a43bf78" class=""> </p></details></li></ul><ul id="2c962788-4fb3-423a-b77e-906c3d4136e3" class="toggle"><li><details open=""><summary>제거</summary><p id="01ee9ce6-b8a4-491a-b1ce-a910276dc8f7" class=""> </p></details></li></ul><ul id="840beaed-c493-4eb9-ab6c-0c06e5badca1" class="toggle"><li><details open=""><summary>(Option) count up down Category</summary><p id="8b4b4b2a-7b23-4e73-885a-b3a1c5c2352f" class=""> </p></details></li></ul></details></li></ul><ul id="6cd08d5e-2d11-4231-8953-6db5abca2d85" class="toggle"><li><details open=""><summary>대시보드</summary><p id="c7b1b251-1238-46a5-9455-cec43818ca64" class=""> </p><p id="2f926c77-37aa-48e1-bdb1-14510c90d0ad" class=""> </p><p id="7c76867a-581f-4a9d-8f8b-b28c751ebbf4" class=""> </p><p id="43998280-310c-43e4-871d-d4821936003f" class=""> </p><p id="0ec31b87-df1f-44b2-a30f-db4058829867" class=""> </p><p id="e7424aa6-8db2-4bea-a057-fb4443b903d9" class=""> </p></details></li></ul><ul id="250c99dc-1421-42a6-8f95-c4b926741e64" class="toggle"><li><details open=""><summary>파일 WR</summary><p id="08d3fff5-be93-472b-ab15-4f178ed49cc1" class=""> </p><p id="9aa7807a-1624-4e64-ae55-e57bb41fee61" class=""> </p><p id="d85fe5ad-7d83-4429-adbe-00176bfd0362" class=""> </p><p id="b5e60c8c-3f1f-4e81-984a-aaa1dc9a772f" class=""> </p><p id="94c74e58-31f8-4fb4-a8d0-8c875eed58ab" class=""> </p></details></li></ul><p id="a98d7367-11f1-4541-a3bc-0747ee5b31c7" class="">
</p></div></details></div></details></div></div></article><span class="sans" style="font-size:14px;padding-top:2em"></span></body></html>