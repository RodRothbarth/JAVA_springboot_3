package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String lugradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;
    public Address(@NotNull Address address) {
        this.lugradouro = address.lugradouro;
        this.bairro = address.bairro;
        this.cep = address.cep;
        this.cidade = address.cidade;
        this.uf = address.uf;
        this.complemento = address.complemento;
        this.numero = address.numero;
    }

    public void updateAddress(AddressData address) {
        if(address.lugradouro() != null){
            this.lugradouro = address.lugradouro();
        }
        if(address.bairro() != null){
            this.bairro = address.bairro();
        }
        if(address.cep() != null){
            this.cep = address.cep();
        }
        if(address.cidade() != null){
            this.cidade = address.cidade();
        }
        if(address.uf() != null){
            this.uf = address.uf();
        }
        if(address.complemento() != null){
            this.complemento = address.complemento();
        }
        if(address.numero() != null){
            this.numero = address.numero();
        }
    }
}
