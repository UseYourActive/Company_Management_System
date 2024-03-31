package com.tinqin.cms;

import com.tinqin.cms.operations.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.tinqin.cms.operations.AssignAssetToEmployeeOperation.*;
import static com.tinqin.cms.operations.CreateNewAssetOperation.*;
import static com.tinqin.cms.operations.DeleteAssetOperation.*;
import static com.tinqin.cms.operations.EditAssetOperation.*;
import static com.tinqin.cms.operations.FindAllAssetsOperation.*;
import static com.tinqin.cms.operations.FindByIdAssetOperation.*;

@FeignClient(name = "ASSET-SERVICE")
public interface AssetFeignClient {
    @GetMapping(path = "/asset/{id}")
    ResponseEntity<FindByIdAssetResponse> findAssetById(@PathVariable(value = "id") String id);

    @GetMapping(path = "/asset/find-all")
    ResponseEntity<FindAllAssetsResponse> findAllAssets(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                        @RequestParam(defaultValue = "2") Integer numberOfBooksPerPage);

    @PostMapping(path = "/asset/create")
    ResponseEntity<CreateNewAssetResponse> createNewAsset(@RequestBody CreateNewAssetRequest request);

    @PatchMapping(path = "/asset/edit")
    ResponseEntity<EditAssetResponse> editAsset(@RequestBody EditAssetRequest request);

    @DeleteMapping(path = "/asset/{id}")
    ResponseEntity<DeleteAssetResponse> deleteAsset(@PathVariable("id") String id);

    @PutMapping(path = "/asset/assign-to-employee")
    ResponseEntity<AssignAssetToEmployeeResponse> assignAssetToEmployee(@RequestBody AssignAssetToEmployeeRequest request);
}
