import cl from '../SignInput/SignInput.module.css'
type SignInputProps = {
placeholder: string
type: string
error?: string
}
export function SignInput({placeholder, type, error, ...props}: SignInputProps) {
    
    return (
        <>
        <input type={type} placeholder={placeholder} className={`${cl["input"]} ${error ? cl["input-error"] : ""}`} 
        {...props}
        />
        
        </>
    )
}