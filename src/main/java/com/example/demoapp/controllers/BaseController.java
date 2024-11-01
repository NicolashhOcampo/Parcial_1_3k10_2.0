package com.example.demoapp.controllers;

import com.example.demoapp.entities.Base;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

public interface BaseController <E extends Base, ID extends Serializable>{
    public ResponseEntity<?> getAll();
    public ResponseEntity<?> getOne(@PathVariable ID id);
    public ResponseEntity<?> delete(@PathVariable ID id);
}
