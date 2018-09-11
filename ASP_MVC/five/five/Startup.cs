using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(five.Startup))]
namespace five
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
