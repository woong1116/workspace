package org.example.springmvc.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @NotEmpty(message = "이름을 입력하세요.")
    private String name;
    @NotEmpty(message = "가격을 입력하세요.")
    @Size(min = 1000, max = 10000, message = "가격은 1,000원~10,000원 사이만 입력됩니다.")
    private int price;
}
