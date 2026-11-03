package br.com.fiap.challenge4.controller;

import br.com.fiap.challenge4.dao.ContaDao;
import br.com.fiap.challenge4.dto.ContaRequestDto;
import br.com.fiap.challenge4.dto.ContaResponseDto;
import br.com.fiap.challenge4.exception.ContaNaoEncontradaException;
import br.com.fiap.challenge4.model.Conta;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("contas")
public class ContaController {

    private ContaDao dao;
    private ModelMapper mapper;

    public ContaController(ContaDao dao, ModelMapper mapper) {
        this.mapper = mapper;
        this.dao = dao;
    }

    //GET
    @GetMapping("/{cd}")
    public ResponseEntity<ContaResponseDto> buscar(@PathVariable int cd) throws SQLException, ContaNaoEncontradaException {
        Conta conta = dao.pesquisarPorCd(cd);
        return ResponseEntity.ok(mapper.map(conta, ContaResponseDto.class)); //Retorna o imovel com o status HTTP 200 OK
    }

    @GetMapping
    public List<ContaResponseDto> listar() throws SQLException {
        //Converte uma lista de Imovel em uma lista de ImovelResponseDto
        return dao.listar().stream().map(conta -> mapper.map(conta, ContaResponseDto.class)).toList();
    }

    //POST
    @PostMapping
    public ResponseEntity<ContaResponseDto> cadastrar(@Valid @RequestBody ContaRequestDto dto,
                                                       UriComponentsBuilder builder) throws SQLException {
        Conta conta = mapper.map(dto, Conta.class);
        dao.cadastrar(conta);

        URI uri = builder.path("/contas/{cd}")
                .buildAndExpand(conta.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(mapper.map(conta, ContaResponseDto.class));
    }

    //PUT
    @PutMapping("/{cd}")
    public ResponseEntity<Void> atualizar(@PathVariable int cd, @Valid @RequestBody ContaRequestDto dto) throws SQLException, ContaNaoEncontradaException{
        Conta conta = mapper.map(dto, Conta.class);
        conta.setCodigo(cd);
        dao.atualizar(conta);
        return ResponseEntity.ok().build(); //Retorna o Status 200 OK
    }

    //DELETE
    @DeleteMapping("/{cd}")
    public ResponseEntity<Void> remover(@PathVariable int cd) throws SQLException, ContaNaoEncontradaException {
        dao.deletar(cd);
        return ResponseEntity.noContent().build(); //Retorna o status 204 no Content
    }
}
