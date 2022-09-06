# Mini Web Crawler

About
----------------------
Assignment Project for ST0316(Advanced Java Programming)
Singapore Polytechnic 
Diploma in Information Technology


Problem Definition
----------------------
Code and implement web crawler which will help retrieve web documents from the Internet based on a search phrase.  
From Wikipedia: A Web crawler starts with a list of URLs to visit, called the seeds. As the crawler visits these URLs, it identifies all the hyperlinks in the page and adds them to the list of URLs to visit, called the crawl frontier. URLs from the frontier are recursively visited according to a set of policies. 


Required following funtionalities:
----------------------
- Enable user to enter a search phrase which may contain more than one word. 
- Send the query to two search engines of your choice concurrently such as yahoo or bing.
- Program will need to analyze the downloaded html source from the first 2 search results from each search engine. This will form the seeds.
- Program will need to analyze the downloaded html source from the seeds to find out the top 10 unique webpages through multi-threading.You would have to look for the specific patterns to retrieve the web site address eg  http://....  as a pattern. You will need to apply suitable regular expressions to find such patterns.
- Ignore advertisements when processing the web content.
- Your application will create 2 separate threads which will download and process each web document to find the web URLs, which will be added to the Queue. 
- Each of these 10 webpages URLs will be added to an appropriate data structure such as Queue as they are found.
- The 2 threads will process the next available and unprocessed web URL once it has finished its current task. They will keep doing so, until the 12 websites are found with its contents downloaded.
- The 10 website URLs and its html page contents (saved locally) should be shown to the user through a GUI (For example, after clicking on a selected URL, the web page content will be shown in a text area). The website URLs are to be displayed in a list in ascending order.
- Keep track of the number of occurrences of the search phrase within the html page and display the number of occurrences. 


Repository Description
----------------------


Kang Jun Hui Bryan (kjhbryan@gmail.com)

Aug 2017

This repository is submitted to Singapore Polytechnic as part of a 
graded assignment for the course ST0316 (Advanced Java Programming).


    
