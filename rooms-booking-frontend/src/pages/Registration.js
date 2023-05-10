import "./styles/Registration.css";
import RegistrationButton from "../components/account/RegistrationButton";
import RegistrationForm from "../components/account/RegistrationForm";
import { useState } from "react";
import { register } from "../service/UserService";
import { useNavigate } from "react-router-dom";

export default function Registration() {
  const [isChosen, setIsChosen] = useState(false);
  const [isHost, setIsHost] = useState(false);
  const navigate = useNavigate();

  const chooseAccountType = (isHost) => {
    setIsChosen(true);
    setIsHost(isHost);
  };

  const registerUser = (user) => {
    register(user, setIsChosen, setIsHost, navigate);
  };
  return (
    <div>
      <div className="alignment">
        {!isChosen && (
          <div>
            <h2>Register as</h2>
            <div className="button-alignment">
              <RegistrationButton
                text="Guest"
                isHost={false}
                onClick={chooseAccountType}
              />
              <RegistrationButton
                text="Host"
                isHost={true}
                onClick={chooseAccountType}
              />
            </div>
          </div>
        )}
        {isChosen && (
          <RegistrationForm isHost={isHost} onSubmit={registerUser} />
        )}
      </div>
    </div>
  );

  /* podaci: ime, prezime, adresa, username, lozinka, mejl, host ili guest */
}
