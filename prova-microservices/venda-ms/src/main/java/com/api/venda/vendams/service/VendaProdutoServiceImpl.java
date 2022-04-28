package com.api.venda.vendams.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.api.venda.vendams.clientHttp.ProdutoFeingClient;
import com.api.venda.vendams.model.Venda;
import com.api.venda.vendams.repository.VendaProdutoRepository;
import com.api.venda.vendams.shared.Produto;
import com.api.venda.vendams.shared.VendaDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaProdutoServiceImpl implements VendaProdutoService {

    private final ModelMapper  MAPPER = new ModelMapper();
    
    @Autowired
    private  VendaProdutoRepository repository;
    @Autowired
    private  ProdutoFeingClient cadastro;

    @Override
    public Optional<List<VendaDto>> listAll() {
         
        if (repository.count() < 1)  {
            return Optional.empty();
        }
        
        List<VendaDto> ListVendaDto = repository.findAll().stream()
        .map(dat -> MAPPER.map(dat, VendaDto.class)).collect(Collectors.toList());

        return Optional.of(ListVendaDto);
    }


    @Override
    public Optional<VendaDto> listUnique (String id) {

        Optional<Venda> repositoryResponse = repository.findById(id);
        
        if(repositoryResponse.isEmpty()) {
            return Optional.empty();
        }

        VendaDto vendaDtoResponse = MAPPER.map(repositoryResponse.get(), VendaDto.class);

        return Optional.of(vendaDtoResponse);
    }


    @Override
    public Optional<VendaDto> postUnique (VendaDto venda) {
        boolean operationSucces = cadastro.putStock(venda.getCodigo(), venda.getQuantidadeVendida());
        
        // se não foi possivel retirar do estoque a venda é cancelada
        if (!operationSucces) {
            return Optional.empty();
        }

        Venda vendaRequest = MAPPER.map(venda, Venda.class);
        Produto produtoRequest = cadastro.getProduto(vendaRequest.getCodigo()).get();
        
        // guardando o produto dentro da venda
        vendaRequest.setProduto(produtoRequest);
        
        // caso não tenha data pre-definida é cadastrado a data atual
        if(vendaRequest.getDataVenda() == null || vendaRequest.getDataVenda().isBlank() || vendaRequest.getDataVenda().isEmpty()) {    
            String actualDate = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
            
            vendaRequest.setDataVenda(actualDate);
        }
        
        Venda repositoryResponse = repository.save(vendaRequest);
        
        return Optional.of(MAPPER.map(repositoryResponse, VendaDto.class));
    }
    
    
}
