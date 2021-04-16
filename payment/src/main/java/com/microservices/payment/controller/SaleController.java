package com.microservices.payment.controller;

import com.microservices.payment.data.dto.SaleDTO;
import com.microservices.payment.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    private final PagedResourcesAssembler<SaleDTO> assembler;

    @Autowired
    public SaleController(SaleService saleService, PagedResourcesAssembler<SaleDTO> assembler) {
        this.saleService = saleService;
        this.assembler = assembler;
    }

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public SaleDTO findById(@PathVariable("id") Long id) {

        SaleDTO saleDTO = saleService.findById(id);
        saleDTO.add(linkTo(methodOn(SaleController.class).findById(id)).withSelfRel());
        return saleDTO;

    }

    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "10") int limit,
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "dateOfTheSale"));

        Page<SaleDTO> sales = saleService.findAll(pageable);
        sales.stream()
                .forEach(p -> p.add(linkTo(methodOn(SaleController.class).findById(p.getId())).withSelfRel()));

        PagedModel<EntityModel<SaleDTO>> pageModel = assembler.toModel(sales);
        return new ResponseEntity<>(pageModel, HttpStatus.OK);

    }

    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
            "application/json", "application/xml", "application/x-yaml" })
    public SaleDTO create(@RequestBody SaleDTO saleDTO) {

        SaleDTO dto = saleService.create(saleDTO);

        dto.add(linkTo(methodOn(SaleController.class).findById(dto.getId())).withSelfRel());
        return dto;

    }
}
