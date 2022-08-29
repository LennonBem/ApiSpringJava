package com.api.CRUD.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("upload")
@Slf4j
public class UploadArquivoController {

    @PostMapping()
    public ResponseEntity<String> salvarArquivo(@RequestParam("file") MultipartFile file){
        log.info("Recebendo o arquivo:" + file.getOriginalFilename());
        String path = "/home/lennonbem/Documentos/Estudo/Web/ANGULAR/api/arquivos/";
        String caminho = path + UUID.randomUUID() + "." + extrairExtensao(file.getOriginalFilename());

        try{
            Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>("Arquivo Carregado com Sucesso!", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Arquivo não carregado:" + e.getMessage(), HttpStatus.CONFLICT);
        }


    }

    private String extrairExtensao(String nomeArquivo) {
        int i = nomeArquivo.lastIndexOf(".");
        return nomeArquivo.substring(i + 1);
    }
}
