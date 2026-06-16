package org.example.restexam.service;

import lombok.RequiredArgsConstructor;
import org.example.restexam.domain.Product;
import org.example.restexam.dto.ProductDTO;
import org.example.restexam.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    //crud

    //상품추가
    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO){
//        dto 로 들고 온 값을 엔티티로 바꿔줘야겠네요.
//        Product product = Product.builder()
//                        .name(productDTO.getName())
//                                .price(productDTO.getPrice())
//                                        .build();


        Product saveProduct = repository.save(Product.fromDTO(productDTO));
//        엔티티에 들어있는 값을 dto에 옮겨 담아야겠네요.
        return ProductDTO.fromEntity(saveProduct);
    }

    //상품조회 (전체)
    @Transactional(readOnly = true)
    public List<ProductDTO> getProducts(){
        return repository.findAll().stream().map(ProductDTO::fromEntity).toList();
    }

    //상품조회 (한건)
    @Transactional(readOnly = true)
    public ProductDTO getProduct(Long id){
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("상품이 존재하지 않아요.  id:: " + id));
        return ProductDTO.fromEntity(product);
    }

    //    상품정보 수정
    @Transactional
    public ProductDTO updateProduct(ProductDTO productDTO){
        Product product = repository.findById(productDTO.getId())
                .orElseThrow(() -> new RuntimeException("수정할 상품이 존재하지 않습니다. "));

//        수정은 어떻게 처리하셨나요??? (처리하신 분들 채팅창에~~ )
        if(productDTO.getName() != null)
            product.setName(productDTO.getName());

//위에서사용한 if 문을 이렇게 사용할 수 있다!!
        Optional.ofNullable(productDTO.getName()).ifPresent(product::setName);

//        Optional.ofNullable(productDTO.getPrice()).ifPresent(product::setPrice);

        if(productDTO.getPrice() != 0)   //int vs Integer  - 차이점!!!
            product.setPrice(productDTO.getPrice());

        return ProductDTO.fromEntity(product);
    }
    //    상품정보 삭제
    @Transactional
    public void deleteProduct(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("삭제할 상품이  존재하지 않습니다. id:: "+id);
        }
        repository.deleteById(id);
    }
}
