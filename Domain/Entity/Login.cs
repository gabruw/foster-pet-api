using Domain.Entity;

namespace Domain.DTO
{
    public class Login : Base
    {
        public long Id { get; set; }
        public string Email { get; set; }
        public string Senha { get; set; }

        public override void Validate()
        {
            ClearValidationMessage();

            if (string.IsNullOrEmpty(Email))
            {
                AddError("Preencha o email.");
            }

            if (string.IsNullOrEmpty(Senha))
            {
                AddError("Preencha o senha.");
            }
        }
    }
}
