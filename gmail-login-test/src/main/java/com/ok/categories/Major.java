package com.ok.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

/**
 * Created by OK on 17.01.2016.
 */

@RunWith(Categories.class)
@Categories.IncludeCategory({Major.class})
public interface Major {

}
