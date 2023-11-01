package mz.sga.ujc.demo.service.paramentrization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.sga.ujc.demo.model.parametrization.Provincia;
import mz.sga.ujc.demo.repository.parametrization.ProvinciaRepository;

@Service
public class ProvinciaService {
    
    @Autowired
    private ProvinciaRepository repository;

    public List<Provincia> listaProvincias(){
        return repository.findAll();
    }
}
