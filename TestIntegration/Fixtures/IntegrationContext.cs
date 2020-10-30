using FosterPet;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.TestHost;
using System.Net.Http;

namespace TestIntegration.Fixtures
{
    public class IntegrationContext
    {
        public HttpClient Client { get; set; }
        private TestServer _server;

        public IntegrationContext()
        {
            SetupClient();
        }

        private void SetupClient()
        {
            _server = new TestServer(new WebHostBuilder().UseStartup<Startup>());
            Client = _server.CreateClient();
        }
    }
}
