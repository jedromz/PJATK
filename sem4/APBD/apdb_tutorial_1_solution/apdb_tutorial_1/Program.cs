using System;
using System.Linq;
using System.Net.Http;
using System.Text.RegularExpressions;

namespace apdb_tutorial_1
{
    class MainClass
    {
        public static async System.Threading.Tasks.Task Main(string[] args)
        {



            //checks if CLArg is a valid URI address, if not https://www.pja.edu.pl is used


            string url = (args.Length > 0 && Uri.IsWellFormedUriString(args[0], UriKind.Absolute)) ? args[0] : "https://www.pja.edu.pl/";
            var httpClient = new HttpClient();
            HttpResponseMessage responseMessage = await httpClient.GetAsync(url);
            if (responseMessage.IsSuccessStatusCode)
            {
                string content = await responseMessage.Content.ReadAsStringAsync();
                // regex to match email pattern
                Regex regex = new Regex(@"\w+([-.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*",
                RegexOptions.IgnoreCase);
                var matches = regex.Matches(content);

                // print only unique emails
                var uniqueMatches = matches 
                            .OfType<Match>()
                            .Select(m => m.Value)
                            .Distinct();

                uniqueMatches.ToList().ForEach(Console.WriteLine);
            }

        }
        
    }
}
