package com.financas.api.controller.enumController;

import com.financas.api.dto.LabelValueDto;
import com.financas.domain.enums.TipoCompraEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "${cors.origin:http://localhost:4200}")
@RestController
@RequestMapping("/enum/tipo-compra")
public class EnumTipoCompraController {
    @GetMapping()
    public ResponseEntity<List<LabelValueDto>> consultarTipoCompras(){
        return ResponseEntity.ok(this.criarListaTipoCompra());
    }

    private List<LabelValueDto> criarListaTipoCompra(){
        List<LabelValueDto> tipos = new ArrayList<>();

        for(TipoCompraEnum tipo: TipoCompraEnum.values()){
            tipos.add(new LabelValueDto(tipo.getChave(), tipo.getValor()));
        }
        return tipos;
    }
}
