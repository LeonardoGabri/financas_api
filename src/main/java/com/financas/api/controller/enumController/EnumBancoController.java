package com.financas.api.controller.enumController;

import com.financas.api.dto.LabelValueDto;
import com.financas.domain.enums.BancoEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/enum/bancos")
public class EnumBancoController {
    @GetMapping()
    public ResponseEntity<List<LabelValueDto>> consultarTodosBancos(){
        return ResponseEntity.ok(this.criarListaBancos());
    }

    private List<LabelValueDto> criarListaBancos(){
        List<LabelValueDto> bancos = new ArrayList<>();

        for(BancoEnum banco: BancoEnum.values()){
            bancos.add(new LabelValueDto(banco.getChave(), banco.getValor()));
        }
        return bancos;
    }
}
