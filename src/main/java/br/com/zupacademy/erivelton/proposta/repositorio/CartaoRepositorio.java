package br.com.zupacademy.erivelton.proposta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.erivelton.proposta.entidade.Cartao;

@Repository
public interface CartaoRepositorio extends JpaRepository<Cartao, String>{

}
