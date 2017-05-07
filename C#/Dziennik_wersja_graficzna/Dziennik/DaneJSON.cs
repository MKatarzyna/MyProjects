using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Dziennik
{
    class DaneJSON
    {
        public DaneBMI BMI { get; set; }
        public DaneCwiczenia Cwiczenia { get; set; }
        public DaneData Data { get; set; }
        public DaneJedzenie Jedzenie { get; set; }
        public DanePlyny Plyny { get; set; }
    }
}
