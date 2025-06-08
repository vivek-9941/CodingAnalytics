package org.vivek.platform.Model.Codechef;

import lombok.Data;

import java.util.List;

@Data
public class CodechefDTO
{

    private String name;
    private int currentRating;
    private int globalRank;
    private String countryName;
    private List<CodechefRatingDTO> ratingData;

}
