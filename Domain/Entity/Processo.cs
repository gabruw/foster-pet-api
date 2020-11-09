using Domain.Entity;
using System;

namespace Domain.Entity
{
    public class Processo : Base
    {
        public long Id { get; set; }
        public DateTime Data { get; set; }
        public string Ocorrencia { get; set; }
        public string Ocorrido { get; set; }

        public override void Validate()
        {
            ClearValidationMessage();

            if (Data == null)
            {
                AddError("Preencha a data.");
            }

            if (string.IsNullOrEmpty(Ocorrencia))
            {
                AddError("Preencha a ocorrencia.");
            }

            if (string.IsNullOrEmpty(Ocorrido))
            {
                AddError("Preencha o ocorrido.");
            }
        }
    }
}
