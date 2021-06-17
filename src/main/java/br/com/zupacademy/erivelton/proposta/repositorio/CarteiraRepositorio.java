package br.com.zupacademy.erivelton.proposta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.erivelton.proposta.entidade.CarteiraDigital;
import br.com.zupacademy.erivelton.proposta.enums.TipoCarteiraDigital;

@Repository
public interface CarteiraRepositorio extends JpaRepository<CarteiraDigital, String>{

	boolean existsByCarteiraAndCartaoId(TipoCarteiraDigital carteira, String id);

}
