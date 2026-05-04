<html>
<head>
<title>Main.java</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.s0 { color: #cf8e6d;}
.s1 { color: #bcbec4;}
.s2 { color: #bcbec4;}
.s3 { color: #7a7e85;}
.s4 { color: #6aab73;}
.s5 { color: #2aacb8;}
</style>
</head>
<body bgcolor="#191a1c">
<table CELLSPACING=0 CELLPADDING=5 COLS=1 WIDTH="100%" BGCOLOR="#606060" >
<tr><td><center>
<font face="Arial, Helvetica" color="#000000">
Main.java</font>
</center></td></tr></table>
<pre><span class="s0">import </span><span class="s1">java</span><span class="s2">.</span><span class="s1">util</span><span class="s2">.</span><span class="s1">Scanner</span><span class="s2">;</span>

<span class="s0">public class </span><span class="s1">Main </span><span class="s2">{</span>
    <span class="s0">public static void </span><span class="s1">main</span><span class="s2">(</span><span class="s1">String</span><span class="s2">[] </span><span class="s1">args</span><span class="s2">) {</span>
        <span class="s1">Scanner sc </span><span class="s2">= </span><span class="s0">new </span><span class="s1">Scanner</span><span class="s2">(</span><span class="s1">System</span><span class="s2">.</span><span class="s1">in</span><span class="s2">);</span>

        <span class="s3">// Accepting user input for name, salary, and calculating yearly salary</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">println</span><span class="s2">(</span><span class="s4">&quot;[Budgeting System]&quot;</span><span class="s2">);</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">print</span><span class="s2">(</span><span class="s4">&quot;Enter your name: &quot;</span><span class="s2">);</span>
        <span class="s1">String name </span><span class="s2">= </span><span class="s1">sc</span><span class="s2">.</span><span class="s1">nextLine</span><span class="s2">();</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">print</span><span class="s2">(</span><span class="s4">&quot;Hello &quot; </span><span class="s2">+ </span><span class="s1">name </span><span class="s2">+ </span><span class="s4">&quot;. &quot;</span><span class="s2">);</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">print</span><span class="s2">(</span><span class="s4">&quot;Please enter your monthly salary: &quot;</span><span class="s2">);</span>
        <span class="s0">double </span><span class="s1">salary </span><span class="s2">= </span><span class="s1">Double</span><span class="s2">.</span><span class="s1">parseDouble</span><span class="s2">(</span><span class="s1">sc</span><span class="s2">.</span><span class="s1">nextLine</span><span class="s2">());</span>
        <span class="s0">double </span><span class="s1">yearly_salary </span><span class="s2">= </span><span class="s1">salary </span><span class="s2">* </span><span class="s5">12</span><span class="s2">;</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">println</span><span class="s2">(</span><span class="s4">' '</span><span class="s2">);</span>

        <span class="s3">// Accepting user input for the loan to calculate principal</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">print</span><span class="s2">(</span><span class="s4">&quot;What is the total amount of your load?: &quot;</span><span class="s2">);</span>
        <span class="s0">double </span><span class="s1">loan_principal </span><span class="s2">= </span><span class="s1">Double</span><span class="s2">.</span><span class="s1">parseDouble</span><span class="s2">(</span><span class="s1">sc</span><span class="s2">.</span><span class="s1">nextLine</span><span class="s2">());</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">print</span><span class="s2">(</span><span class="s4">&quot;What is the interest rate of your load?: &quot;</span><span class="s2">);</span>
        <span class="s0">double </span><span class="s1">loan_interest </span><span class="s2">= </span><span class="s1">Double</span><span class="s2">.</span><span class="s1">parseDouble</span><span class="s2">(</span><span class="s1">sc</span><span class="s2">.</span><span class="s1">nextLine</span><span class="s2">());</span>
        <span class="s1">loan_interest </span><span class="s2">/= </span><span class="s5">100</span><span class="s2">;</span>
        <span class="s0">double </span><span class="s1">new_principal </span><span class="s2">= </span><span class="s1">loan_principal </span><span class="s2">* </span><span class="s1">Math</span><span class="s2">.</span><span class="s1">pow</span><span class="s2">((</span><span class="s5">1 </span><span class="s2">+ (</span><span class="s1">loan_interest </span><span class="s2">/ </span><span class="s5">12</span><span class="s2">)), </span><span class="s5">12</span><span class="s2">);</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">println</span><span class="s2">(</span><span class="s4">' '</span><span class="s2">);</span>

        <span class="s3">// Displaying salary information and loan's principal</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">println</span><span class="s2">(</span><span class="s4">&quot;Your yearly salary is $&quot; </span><span class="s2">+ </span><span class="s1">yearly_salary</span><span class="s2">);</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">println</span><span class="s2">(</span><span class="s4">&quot;In 12 months, your loan's principal will be $&quot; </span><span class="s2">+ </span><span class="s1">new_principal</span><span class="s2">);</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">println</span><span class="s2">(</span><span class="s4">' '</span><span class="s2">);</span>

        <span class="s3">// Displaying whether loan will be paid off</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">println</span><span class="s2">(</span><span class="s4">&quot;At the end of the year, you will have paid off your debt: &quot; </span><span class="s2">+ (</span><span class="s1">yearly_salary </span><span class="s2">&gt;= </span><span class="s1">new_principal</span><span class="s2">));</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">println</span><span class="s2">(</span><span class="s4">&quot;At the end of the year, you will still have some debt left: &quot; </span><span class="s2">+ (</span><span class="s1">yearly_salary </span><span class="s2">&lt; </span><span class="s1">new_principal</span><span class="s2">));</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">println</span><span class="s2">(</span><span class="s4">&quot;At the end of the year, you will have $&quot; </span><span class="s2">+ (</span><span class="s1">yearly_salary </span><span class="s2">- </span><span class="s1">new_principal</span><span class="s2">) + </span><span class="s4">&quot; of your salary left&quot;</span><span class="s2">);</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">println</span><span class="s2">(</span><span class="s4">' '</span><span class="s2">);</span>

        <span class="s3">// Accepting user input to verify potential loan relief and display result corresponding</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">println</span><span class="s2">(</span><span class="s4">&quot;The government is offering loan relief for persons 25 and under, and for those 65 and over.&quot;</span><span class="s2">);</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">print</span><span class="s2">(</span><span class="s4">&quot;What is your age?: &quot;</span><span class="s2">);</span>
        <span class="s0">int </span><span class="s1">age </span><span class="s2">= </span><span class="s1">Integer</span><span class="s2">.</span><span class="s1">parseInt</span><span class="s2">(</span><span class="s1">sc</span><span class="s2">.</span><span class="s1">nextLine</span><span class="s2">());</span>
        <span class="s0">boolean </span><span class="s1">eligible_for_relief </span><span class="s2">= (</span><span class="s1">age </span><span class="s2">&lt;= </span><span class="s5">25</span><span class="s2">) || (</span><span class="s1">age </span><span class="s2">&gt;= </span><span class="s5">65</span><span class="s2">);</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">println</span><span class="s2">(</span><span class="s4">&quot;The relief is $10,000. You are eligible for the relief: &quot; </span><span class="s2">+ </span><span class="s1">eligible_for_relief</span><span class="s2">);</span>
        <span class="s0">boolean </span><span class="s1">loan_paid_off </span><span class="s2">= (</span><span class="s1">yearly_salary </span><span class="s2">&gt;= </span><span class="s1">new_principal</span><span class="s2">) || (</span><span class="s1">eligible_for_relief </span><span class="s2">&amp;&amp; ((</span><span class="s1">yearly_salary </span><span class="s2">+ </span><span class="s5">10000</span><span class="s2">) &gt;= </span><span class="s1">new_principal</span><span class="s2">));</span>
        <span class="s1">System</span><span class="s2">.</span><span class="s1">out</span><span class="s2">.</span><span class="s1">println</span><span class="s2">(</span><span class="s4">&quot;With or without relief, you will be able to pay your loan in full: &quot; </span><span class="s2">+ </span><span class="s1">loan_paid_off</span><span class="s2">);</span>
    <span class="s2">}</span>
<span class="s2">}</span></pre>
</body>
</html>