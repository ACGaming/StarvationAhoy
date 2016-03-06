package dmfmm.StarvationAhoy.Client.Gui.book_gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class BookPageGui extends GuiScreen {

    public Map<int[], String> links = new HashMap<>();

    public static Map<String, String> change_newline_to_newline = new HashMap<>();

    static ResourceLocation IMG_LOCACATION = new ResourceLocation("starvationahoy", "textures/gui/Infobooknexist.png");
    static int IMG_WIDTH = 280;

    int page = 0;
    int numpages = 0;


    static int IMG_HEIGHT = 100;

    int x_x = 0;
    int y_y = 0;
    int ox = 0;
    int oy = 0;

    static int IMG_START_X_P1 = 24;
    static int IMG_START_X_P2 = BookPage.PAGE_WIDTH + 37;
    static int IMG_START_Y_P1 = 15;
    static int IMG_START_Y_P2 = 15;

    static {

        change_newline_to_newline.put("newline", "\n");

    }

    boolean initLists = true;

    public BookPage myPage;

    public BookPageGui(String id){
        String thingy = "starvationahoy.book_data.page.test";
        String data = StatCollector.translateToLocal(thingy);
        myPage = new BookPage();
        myPage.digestString(data);
        try {
            myPage.rasterizeToLayout(this);
            numpages = myPage.numpages;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void drawScreen(int a, int b, float c){
        System.out.println(c);
        System.out.println(a);
        System.out.println(b);
        this.drawDefaultBackground();
        this.drawBackGround(c);
        GL11.glDisable(GL11.GL_LIGHTING);
        this.drawAllElements();
        GL11.glEnable(GL11.GL_LIGHTING);
        super.drawScreen(a, b, c);

    }

    public void initGui(){
        int x = (int)(this.width - 256) / 2;
        int y = (int)(this.height - 202) / 2;
        ox = x;
        oy = y;
        this.buttonList.add(new Buttons.NextPage(0, ox + Buttons.ARROW_LEFT_POS_X, oy + Buttons.ARROW_LEFT_POS_Y, false));
        this.buttonList.add(new Buttons.NextPage(1, ox + Buttons.ARROW_NEXT_POS_X, oy + Buttons.ARROW_NEXT_POS_Y, true));

    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0){
            if (this.page - 2 > -1){
                page -= 2;
            }

        }
        if (button.id == 1){
            if (this.page + 2 < this.numpages+1){
                page += 2;
            }
        }
    }

    public void drawBackGround(float c){

        int x = (int)(this.width - 256) / 2;
        int y = (int)(this.height - 202) / 2;
        ox = x;
        oy = y;

        GL11.glColor3d(1.0, 1.0, 1.0);

        this.mc.getTextureManager().bindTexture(IMG_LOCACATION);
        this.drawTexturedModalRect((int)x, (int)y, 0,0, IMG_WIDTH, IMG_HEIGHT);


    }

    public void drawAllElements() {
        for (BookElement element : this.myPage.elements){

            if (element.page == this.page || element.page == this.page + 1){

            if (element.page == this.page){
                x_x = ox + this.IMG_START_X_P1;
                y_y = oy + this.IMG_START_Y_P1;
            }
            else {
                x_x = ox + this.IMG_START_X_P2;
                y_y = oy + this.IMG_START_Y_P2;
            }

           // System.out.println("Testsetstestestest");
            try {
                Method drawer = this.getClass().getMethod("drawElement" + element.drawFunction, BookElement.class);

                drawer.invoke(this, element);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            }
        }
    }



    /*

    drawElement Schema:

    BookElement: the element
    int no.1: what to do: 0 for get height, 1+ for segments
    int no.2: if first int is 1+ contains the height from when called with first int=0

    */

    public int toArmorCode(int r, int g, int b){
        return b + 256 * g + 65536 * r;
    }

    public ArrayList<BookElement> splitElementImage(BookElement element){

        int space = Integer.parseInt(element.args.get(1));

        int placepage = element.page;
        int placex = element.x;
        int placey = element.y;

        if (BookPage.PAGE_HEIGHT - element.y < space){
            placepage += 1;
            placex = 0;
            placey = 0;
        }

        ArrayList<BookElement> re = new ArrayList<>();
            re.add(new BookElement("Image", placex, placey, placepage, element.args, element.data));
        re.add(new BookElement("EndMarker", 0, placey + space, placepage, null, null));
        return re;


    }

    public ArrayList<BookElement> splitElementCrafting(BookElement element){
        int space = BookPage.PAGE_HEIGHT;

        int placepage = element.page;
        int placex = element.x;
        int placey = element.y;

        if (BookPage.PAGE_HEIGHT - element.y < space){
            placepage += 1;
            placex = 0;
            placey = 0;
        }

        ArrayList<BookElement> re = new ArrayList<>();
        re.add(new BookElement("Crafting", placex, placey, placepage, element.args, element.data));
        re.add(new BookElement("EndMarker", 0, placey + space, placepage, null, null));
        return re;
    }


    public ArrayList<BookElement> splitElementHungerStats(BookElement element){
        int space = BookPage.PAGE_HEIGHT;

        int placepage = element.page;
        int placex = element.x;
        int placey = element.y;

        if (BookPage.PAGE_HEIGHT - element.y < space){
            placepage += 1;
            placex = 0;
            placey = 0;
        }

        ArrayList<BookElement> re = new ArrayList<>();
        re.add(new BookElement("HungerStats", placex, placey, placepage, element.args, element.data));
        re.add(new BookElement("EndMarker", 0, placey + space, placepage, null, null));
        return re;
    }

    public void drawElementHungerStats(BookElement element){
        int i1 = BookPage.PAGE_WIDTH;
        int j1 = BookPage.PAGE_HEIGHT;
        if ((element.y > j1 + 19)){
            return;
        }
        else {
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glColor4f(1f, 1f, 1f, 1f);


            int x = element.x + x_x;
            int y = element.y + y_y;

            this.mc.renderEngine.bindTexture(new ResourceLocation("starvationahoy:/textures/gui/book/BookIcons.png"));
            this.drawTexturedModalRect(x + 3, y + 30, 0, 0, 12, 24);
            this.drawTexturedModalRect(x + 3, y + 50, 12, 0, 12, 24);
            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            //FontRenderer fontr = this.fontRendererObj;
            fontRendererObj.setUnicodeFlag(true);
            fontRendererObj.drawStringWithShadow("Hunger Stats:", x + 19, y, 0x3333FF);
            fontRendererObj.setUnicodeFlag(false);

            ArrayList<String> args = element.args;

            if (args.size() > 2){
                if(args.get(2).toString().length() > 5){
                    if(args.get(2).toString().length() > 12){
                        fontRendererObj.drawString((String) args.get(2), x + (fontRendererObj.getStringWidth((String) args.get(2)) / 5 -4), y + 15, 0x0000000);
                    } else {
                        fontRendererObj.drawString((String) args.get(2), x + (fontRendererObj.getStringWidth((String) args.get(2)) / 5), y + 15, 0x0000000);
                    }
                } else {
                    fontRendererObj.drawString((String) args.get(2), x + (fontRendererObj.getStringWidth((String) args.get(2)) / 1), y + 15, 0x0000000);
                }
            }
            fontRendererObj.drawString(String.valueOf(args.get(0)), x + 16 + 50, y + 32, 0x0000000);
            fontRendererObj.drawString(String.valueOf(args.get(1)), x + 16 + 50, y + 52, 0x0000000);

            return;
        }
    }


    public void drawElementCrafting(BookElement element){

        RenderItem r = this.itemRender;

        int base_x = 20 + x_x;
        int base_y = 50 + y_y;


        this.fontRendererObj.setUnicodeFlag(false);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("starvationahoy:/textures/gui/book/Crafting.png"));
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_BLEND);
        this.drawTexturedModalRect(base_x - 14, base_y - 51, 0, 0, BookPage.PAGE_WIDTH, BookPage.PAGE_HEIGHT);
        CraftingProxyHelper cpx = new CraftingProxyHelper(new ItemStack(GameRegistry.findItem(element.args.get(0).split(":")[0], element.args.get(0).split(":")[1])));

        GL11.glDisable(GL11.GL_BLEND);




    }


    public void drawElementImage(BookElement element){
        String image = element.args.get(4);
        int xw = Integer.parseInt(element.args.get(0));
        int yw = Integer.parseInt(element.args.get(1));
        int p = Integer.parseInt(element.args.get(2));
        int y = Integer.parseInt(element.args.get(3));

        ResourceLocation l = new ResourceLocation(image);

        mc.renderEngine.bindTexture(l);

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);

        this.drawTexturedModalRect(x_x + element.x, y_y + element.y, p, y, xw, yw);

        GL11.glEnable(GL11.GL_LIGHTING);
    }

    @Override
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) throws IOException {
        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);

        int x = p_73864_1_;
        int y = p_73864_2_;

        for (int[] i : this.links.keySet()){
            if (x > i[0] && x < i[2] && y > i[1] && y < i[3]){

                page = 0;
                String thingy = this.links.get(i);
                links = new HashMap<>();
                String data = StatCollector.translateToLocal(thingy);
                myPage = new BookPage();
                myPage.digestString(data);
                try {
                    myPage.rasterizeToLayout(this);
                    numpages = myPage.numpages;

                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }


            }
        }

    }

    public ArrayList<BookElement> splitElementTextBlock(BookElement element){

        ArrayList<BookElement> elements = new ArrayList<>();

        int xpos = element.x;
        int ypos = element.y;
        int page = element.page;

        boolean nosplit = false;

        int maximum = BookPage.PAGE_WIDTH - element.x - 2;
        int remain = BookPage.PAGE_HEIGHT - element.y;

        GL11.glColor3f(0.0f, 0.0f, 0.0f);

        BookElement current = new BookElement("TextBlock", xpos, ypos, page, element.args, new ArrayList<BookElement.Token>());

        ArrayList<BookElement.Token> ctrlTokens = new ArrayList<>();

        for (BookElement.Token token : element.data){
            switch (token.type){
                case 0:
                    String[] words = token.data.split(" ");
                    BookElement.Token new_token = new BookElement.Token("");
                    for (String word : words){
                        String next_word = word + " ";
                        int x_disp = Minecraft.getMinecraft().fontRendererObj.getStringWidth(word) + Minecraft.getMinecraft().fontRendererObj.getCharWidth(' ');
                        if (xpos + x_disp > maximum){
                            xpos = -x_disp;
                            if (ypos + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT > remain && !nosplit){
                                ypos = 0;
                                xpos = -x_disp;
                                page += 1;
                                remain = BookPage.PAGE_HEIGHT;
                                current.data.add(new_token);
                                new_token = new BookElement.Token("");
                                elements.add(current);
                                current = new BookElement("TextBlock", 0, 0, page, element.args, (ArrayList<BookElement.Token>) ctrlTokens.clone());
                            }
                            else {
                                ypos += Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
                            }

                        }
                        xpos += x_disp;
                        new_token.data += next_word;
                    }
                    current.data.add(new_token);
                    break;
                case 1:
                    if (token.data.equals("nsplit")){
                        nosplit = !nosplit;
                    }


                    current.data.add(token);

                    if (!token.data.equals("newline")){
                        ctrlTokens.add(token);
                    }

                    else {
                        if (ypos + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT > remain){
                            ypos = 0;
                            xpos = 0;
                            page += 1;
                            remain = BookPage.PAGE_HEIGHT;
                            elements.add(current);
                            current = new BookElement("TextBlock", 0, 0, page, element.args, (ArrayList<BookElement.Token>) ctrlTokens.clone());
                        }
                        else {
                            ypos += Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
                        }
                    }
                    break;
                case 2:
                if (Objects.equals(token.value, "align")) {
                    if (token.data.equals("center")) {
                        ypos += fontRendererObj.FONT_HEIGHT;
                    } else {
                        ypos += fontRendererObj.FONT_HEIGHT;
                    }
                }
                    current.data.add(token);
                    ctrlTokens.add(token);
                    break;

            }
        }
        if (element.args.size() == 0) { element.args.add("0"); }
        int style = Integer.parseInt(element.args.get(0));
        if (style == 1) {
          //  ypos += mc.fontRendererObj.FONT_HEIGHT;
        }
        elements.add(current);
        elements.add(new BookElement("EndMarker", xpos, ypos, page, null, null));
        return elements;
    }

    public Object drawElementTextBlock(BookElement element){

        GL11.glDisable(GL11.GL_LIGHTING);

        // Require Switch 0 = getMinHeight
        // 1 = drawSegment1
        // 2+ = drawSegment2+

        this.fontRendererObj.setUnicodeFlag(true);

        HashMap<String, Integer> colors = new HashMap<String, Integer>() {{
            this.put("red", toArmorCode(255, 85, 85));
            this.put("blue", toArmorCode(85, 85, 255));
            this.put("green", toArmorCode(35, 255, 35));
            this.put("orange", toArmorCode(255, 165, 0));
            this.put("silver", toArmorCode(120, 120, 120));
            this.put("purple", toArmorCode(128, 0, 128));
            this.put("yellow", toArmorCode(255, 255, 0));
            this.put("white", toArmorCode(255, 255, 255));

            this.put("defualt", toArmorCode(255, 255, 255));
            this.put("black", toArmorCode(0, 0, 0));
        }};

        // calculate segment

        int xpos = element.x;
        int ypos = element.y;

        int maximum = BookPage.PAGE_WIDTH - element.x - 2;
        System.out.println(maximum);

        int currcolor = toArmorCode(255, 255, 255);
        if (element.args.size() == 0) { element.args.add("0"); }
        int style = Integer.parseInt(element.args.get(0));
        int line_status = 0;
        boolean drawing_link = false;
        String link_id = "";

        int line_start_x = 0;
        int line_start_y = 0;
        int line_end_x = 0;
        int line_end_y = 0;

        int cent_extra = 0;
        HashMap<Integer, Integer> lens = new HashMap<>();
        int i = 0;
        if (style == 1) {

            for (String s : fontRendererObj.listFormattedStringToWidth(BookPage.tokenized(element.data, new HashMap<String, String>() {{
                put("newline", "\n");
            }}
            ), maximum)) {
                lens.put(i, fontRendererObj.getStringWidth(s));
                i += 1;
            }
        }
        HashMap<Integer, Integer> lens2 = (HashMap<Integer, Integer>) lens.clone();
        int j = 0;
        for (BookElement.Token t : element.data){
            switch (t.type){
                case 0:

                    String origString = t.data;
                    int thecolor = currcolor;


                        if (style == 0){
                            cent_extra = 0;
                            String process = "";
                            boolean done = false;

                            String[] words = t.data.split(" ");
                            for (String word : words){
                                origString = word;
                                if ((line_status & 1) == 1 || drawing_link == true){
                                    origString = "§n" + origString;
                                }
                                if ((line_status & 2) == 2){
                                    origString = "§l" + origString;
                                }
                                if ((line_status & 4) == 4){
                                    origString = "§o" + origString;
                                }
                                if ((line_status & 8) == 8){
                                    origString = "§m" + origString;
                                }
                                if ((line_status & 16) == 16){
                                    origString = "§k" + origString;
                                }
                                int x_displacement = fontRendererObj.getStringWidth(word) + fontRendererObj.getCharWidth(' ');
                                if (xpos + x_displacement > maximum){
                                    xpos = element.x;
                                    ypos += this.fontRendererObj.FONT_HEIGHT;
                                    if (drawing_link){
                                        if (!initLists){

                                        }
                                        else {
                                        links.put(new int[] {line_start_x + x_x, line_start_y + y_y, line_end_x + x_x, line_end_y + y_y}, link_id);
                                        line_start_x = xpos;
                                        line_start_y = ypos;
                                        line_end_y = ypos + this.fontRendererObj.FONT_HEIGHT;
                                        line_end_x = xpos;}
                                    }
                                }

                                int docolor = currcolor;
                                if (drawing_link){
                                    docolor = toArmorCode(0, 0, 255);
                                }

                                if (drawing_link){
                                    line_end_x += x_displacement;
                                }

                                fontRendererObj.drawString(origString + " ", xpos + x_x, ypos + y_y, docolor);
                                xpos += x_displacement;
                            }

                        }
                        else {
                            if ((line_status & 1) == 1 || drawing_link == true){
                                origString = "§n" + origString;
                            }
                            if ((line_status & 2) == 2){
                                origString = "§l" + origString;
                            }
                            if ((line_status & 4) == 4){
                                origString = "§o" + origString;
                            }
                            if ((line_status & 8) == 8){
                                origString = "§m" + origString;
                            }
                            if ((line_status & 16) == 16) {
                                origString = "§k" + origString;

                            }
                            cent_extra = xpos - element.x;
                            String last = this.fontRendererObj.listFormattedStringToWidth(origString, maximum - cent_extra).get(this.fontRendererObj.listFormattedStringToWidth(origString, maximum - cent_extra).size()-1);
                            String[] words = origString.split(" ");
                            for (String s : words){
                                String str = (String)s + " ";
                                int len = fontRendererObj.getStringWidth(str);
                                int remain = lens.get(j) - len;
                                if (lens.get(j) == lens2.get(j)) {
                                    xpos = element.x + (maximum / 2) - (lens2.get(j) / 2);
                                }
                                lens.put(j, remain);
                                fontRendererObj.drawString(str, xpos + x_x, ypos + y_y, currcolor);
                                xpos += len;
                                if (remain <= 0) {
                                    j += 1;
                                    ypos += fontRendererObj.FONT_HEIGHT;
                                }
                            }

                        }



                    break;
                case 1:

                    switch (t.data){
                        case "newline":
                            if (style == 0) { ypos += this.fontRendererObj.FONT_HEIGHT; }

                            xpos = element.x;
                            break;
                        case "endlink":
                            drawing_link = false;
                            if (initLists){
                                links.put(new int[] {line_start_x + x_x, line_start_y + y_y, line_end_x + x_x, line_end_y + y_y}, link_id);
                                line_start_x = xpos;
                                line_start_y = ypos;
                                line_end_y = ypos + this.fontRendererObj.FONT_HEIGHT;
                                line_end_x = xpos;
                            }
                            break;

                    }

                    break;

                case 2:
                    switch (t.value){
                        case "color":


                            if (colors.containsKey(t.data)){
                                currcolor = colors.get(t.data);
                                System.out.println("hrll");
                            }
                            else {
                                System.out.println("hrll");
                                String[] stuff = t.data.split(",");
                                currcolor = toArmorCode(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]), Integer.parseInt(stuff[2]));
                            }
                            break;
                        case "align":
                            if (t.data.equals("center")){
                                ypos += fontRendererObj.FONT_HEIGHT;
                                style = 1;
                            }
                            else {
                                style = 0;
                                ypos += fontRendererObj.FONT_HEIGHT;
                            }
                            break;
                        case "style":
                            switch (t.data){
                                case "bold":
                                    line_status += 2;
                                    break;
                                case "italic":
                                    line_status += 4;
                                    break;
                                case "underlined":
                                case "underline":
                                    line_status += 1;
                                    break;
                                case "strike":
                                case "strikethrough":
                                case "crossedout":
                                    line_status += 8;
                                    break;
                                case "garbled":
                                case "obsfucated":
                                case "random":
                                    line_status += 16;
                                    break;
                                case "regular":
                                case "normal":
                                case "n":
                                    line_status = 0;
                                    break;
                            }
                            break;
                        case "link":
                            link_id = t.data;
                            line_start_x = xpos;
                            line_start_y = ypos;
                            line_end_x = xpos;
                            line_end_y = ypos + this.fontRendererObj.FONT_HEIGHT;
                            drawing_link = true;
                    }
            }
        }


        this.fontRendererObj.setUnicodeFlag(false);
        initLists = false;
        return null;


    }

}
