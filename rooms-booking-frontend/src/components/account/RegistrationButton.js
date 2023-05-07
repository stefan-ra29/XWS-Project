import "./RegistrationButton.css";

export default function RegistrationButton({ text, isHost, onClick }) {
  const selectAccountType = (e) => {
    onClick(isHost ? true : false);
  };
  return <button onClick={selectAccountType}>{text}</button>;
}
