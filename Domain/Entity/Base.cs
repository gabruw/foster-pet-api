using System.Collections.Generic;
using System.Linq;

namespace Domain.Entity
{
    public abstract class Base
    {
        private List<string> ValidationMessage = new List<string>();

        public List<string> GetValdiationMessage
        {
            get
            {
                return ValidationMessage ?? (ValidationMessage = new List<string>());
            }
        }

        protected void ClearValidationMessage()
        {
            ValidationMessage.Clear();
        }

        protected void AddError(string message)
        {
            ValidationMessage.Add(message);
        }

        public bool isValid
        {
            get
            {
                return !ValidationMessage.Any();
            }
        }

        public abstract void Validate();
    }
}
