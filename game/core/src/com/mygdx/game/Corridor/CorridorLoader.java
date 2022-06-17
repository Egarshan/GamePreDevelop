package com.mygdx.game.Corridor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourcesClass;

public class CorridorLoader implements Screen {

    private Texture loadIcon1, loadIcon2;
    private Vector2 loadIcon1Position, loadIcon2Position;
    private float loadIcon1Width, loadIcon1Height, loadIcon2Width, loadIcon2Height;
    private float alfa;

    private AssetManager manager;
    private MyGdxGame game;
    private TextureRegion[] background;
    private TextureRegion[] diary, diaryText0, diaryText1, diaryText2, diaryText3, diaryText4, diaryText5, diaryText6;
    private TextureRegion[] girlStand, girlWalk, girlHit, girlTakesItem;
    private TextureRegion[] items;
    private TextureRegion[] interfaceElements;

    private TextureRegion[] dialogElements;

    private TextureRegion[] exitConfirm;

    private TextureRegion[] inventory, inventoryIcons, activeCell, hammerIcon;

    private TextureRegion[] backgroundAdditional;

    private TextureRegion[] playerMask, maskSelector, maskSelectorButtons, maskSelectorIcons;

    public CorridorLoader(MyGdxGame game) {
        this.game = game;

        loadIcon1 = new Texture(Gdx.files.internal("иконка 1.png"));
        loadIcon2 = new Texture(Gdx.files.internal("иконка 2.png"));

        loadIcon1Width = MyGdxGame.WIDTH/10f;
        loadIcon1Height = loadIcon1Width*1.338f;
        loadIcon2Width = loadIcon1Width;
        loadIcon2Height = loadIcon1Height;

        loadIcon2Position = new Vector2(MyGdxGame.WIDTH-loadIcon1Width-loadIcon1Width/2f, 0);
        loadIcon1Position = new Vector2(loadIcon2Position.x, loadIcon2Position.y);

        manager = new AssetManager();
        ResourcesClass.disposeResources();

        manager.load("фон коридора.png", Texture.class);
        manager.load("фон коридора_no_light.png", Texture.class);
        manager.load("девочка стоит.png", Texture.class);
        manager.load("девочка идет.png", Texture.class);
        manager.load("девочка бьет молотком.png", Texture.class);
        manager.load("девочка берет предмет.png", Texture.class);
        manager.load("items.png", Texture.class);
        manager.load("glass_broke.png", Texture.class);
        manager.load("Дневник настоящее.png", Texture.class);
        manager.load("Дневник прошлое.png", Texture.class);
        manager.load("diary.png", Texture.class);
        manager.load("diary.past_1.png", Texture.class);
        manager.load("diary.past_2.png", Texture.class);
        manager.load("diary.past_3.png", Texture.class);
        manager.load("diary.past_4.png", Texture.class);
        manager.load("diary.past_5.png", Texture.class);
        manager.load("diary.past_6.png", Texture.class);
        manager.load("interface.png", Texture.class);
        manager.load("dialogs.png", Texture.class);
        manager.load("confirmExit.png", Texture.class);

        manager.load("Inventory/Инвентарь.png", Texture.class);
        manager.load("Inventory/Инвентарь_ячейки.png", Texture.class);
        manager.load("Inventory/Активная ячейка.png", Texture.class);
        manager.load("Inventory/Молоток_icon.gif", Texture.class);
        manager.load("Inventory/Ключ_icon.png", Texture.class);

        manager.load("mask/печаль.png", Texture.class);
        manager.load("mask/гнев.png", Texture.class);

        manager.load("затемненный экран.png", Texture.class);
        manager.load("mask/селектор.png", Texture.class);
        manager.load("mask/селектор_color.png", Texture.class);

        manager.load("mask/селектор_гнев.png", Texture.class);
        manager.load("mask/селектор_печаль.png", Texture.class);

        manager.load("mask/selector_icons/селектор_гнев_icon.png", Texture.class);
        manager.load("mask/selector_icons/селектор_печаль_icon.png", Texture.class);

        manager.load("полупрозрачный экран.png", Texture.class);
    }

    private void unpackBackground() {
        background = new TextureRegion[2];

        background[0] = new TextureRegion((Texture)manager.get("фон коридора.png"));
        background[1] = new TextureRegion((Texture)manager.get("фон коридора_no_light.png"));

        backgroundAdditional = new TextureRegion[2];
        backgroundAdditional[0] = new TextureRegion((Texture)manager.get("полупрозрачный экран.png"));
        backgroundAdditional[1] = new TextureRegion((Texture)manager.get("затемненный экран.png"));
    }

    public void unpackDiary() {
        diary = new TextureRegion[2];
        diary[0] = new TextureRegion((Texture)manager.get("Дневник настоящее.png"));
        diary[1] = new TextureRegion((Texture)manager.get("Дневник прошлое.png"));

        diaryText0 = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("diary.png"), 1, 576, 505, 445),
                new TextureRegion((Texture)manager.get("diary.png"), 1, 1, 505, 164),
                new TextureRegion((Texture)manager.get("diary.png"), 1, 167, 520, 407),
                new TextureRegion((Texture)manager.get("diary.png"), 508, 926, 506, 95),
                new TextureRegion((Texture)manager.get("diary.png"), 508, 832, 506, 92)
        };
    }

    public void unpackInventory() {
        inventory = new TextureRegion[3];
        inventory[0] = new TextureRegion((Texture)manager.get("Inventory/Инвентарь.png"));
        inventory[1] = new TextureRegion((Texture)manager.get("Inventory/Инвентарь_ячейки.png"));
        inventory[2] = new TextureRegion((Texture)manager.get("Inventory/Активная ячейка.png"));

        inventoryIcons = new TextureRegion[2];
        inventoryIcons[0] = new TextureRegion((Texture)manager.get("Inventory/Молоток_icon.gif"));
        inventoryIcons[1] = new TextureRegion((Texture)manager.get("Inventory/Ключ_icon.png"));
    }

    public void unpackDiaryPast() {
        diaryText1 = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("diary.past_1.png"), 530, 238, 546, 776),
                new TextureRegion((Texture)manager.get("diary.past_1.png"), 1, 179, 527, 835),
                new TextureRegion((Texture)manager.get("diary.past_1.png"), 1, 1, 516, 176)
        };
        diaryText2 = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("diary.past_2.png"), 1, 1, 519, 367),
                new TextureRegion((Texture)manager.get("diary.past_2.png"), 530, 450, 523, 343),
                new TextureRegion((Texture)manager.get("diary.past_2.png"), 1, 370, 527, 423),
                new TextureRegion((Texture)manager.get("diary.past_2.png"), 1055, 508, 557, 285)
        };
        diaryText3 = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("diary.past_3.png"), 568, 573, 505, 365),
                new TextureRegion((Texture)manager.get("diary.past_3.png"), 1, 597, 565, 341),
                new TextureRegion((Texture)manager.get("diary.past_3.png"), 1, 220, 521, 375),
                new TextureRegion((Texture)manager.get("diary.past_3.png"), 1075, 841, 505, 97),
                new TextureRegion((Texture)manager.get("diary.past_3.png"), 1, 1, 505, 217)
        };
        diaryText4 = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("diary.past_4.png"), 1, 1, 505, 464),
                new TextureRegion((Texture)manager.get("diary.past_4.png"), 1041, 668, 505, 247),
                new TextureRegion((Texture)manager.get("diary.past_4.png"), 1, 467, 522, 448),
                new TextureRegion((Texture)manager.get("diary.past_4.png"), 525, 653, 514, 262),
                new TextureRegion((Texture)manager.get("diary.past_4.png"), 1548, 122, 143, 793),
                new TextureRegion((Texture)manager.get("diary.past_4.png"), 1693, 122, 143, 793)
        };
        diaryText5 = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("diary.past_5.png"), 1, 1, 504, 428),
                new TextureRegion((Texture)manager.get("diary.past_5.png"), 520, 526, 582, 330),
                new TextureRegion((Texture)manager.get("diary.past_5.png"), 1, 431, 517, 425),
                new TextureRegion((Texture)manager.get("diary.past_5.png"), 1104, 572, 514, 284)
        };
        diaryText6 = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("diary.past_6.png"), 508, 316, 506, 422),
                new TextureRegion((Texture)manager.get("diary.past_6.png"), 508, 24, 506, 290),
                new TextureRegion((Texture)manager.get("diary.past_6.png"), 1, 1, 505, 737)
        };
    }

    private void unpackGirlStand() {
        girlStand = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1, 3145, 382, 523),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1, 2620, 382, 523),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 385, 3145, 382, 523),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1, 2095, 382, 523),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 385, 2620, 382, 523),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 769, 3145, 382, 523),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 769, 2621, 382, 522),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1153, 3146, 382, 522),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1153, 2623, 382, 521),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 769, 1575, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1153, 2101, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1537, 2625, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1921, 3148, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1, 1, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 385, 526, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 769, 1053, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1153, 1579, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1537, 2103, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1921, 2626, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 2305, 3148, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 385, 4, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 769, 531, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1153, 1057, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1537, 1581, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1921, 2104, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 2305, 2626, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 2689, 3148, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 769, 9, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1153, 535, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1537, 1059, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1921, 1582, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 2305, 2104, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 2689, 2626, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 3073, 3148, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1153, 13, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1537, 537, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1921, 1060, 382, 520),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1537, 3147, 382, 521),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1, 523, 382, 521),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 385, 1048, 382, 521),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1, 1046, 382, 522),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 385, 1571, 382, 522),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 769, 2097, 382, 522),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 1, 1570, 382, 523),
                new TextureRegion((Texture)manager.get("девочка стоит.png"), 385, 2095, 382, 523)
        };
    }

    public void unpackGirlWalk() {
        girlWalk = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("девочка идет.png"), 1, 1045, 380, 520),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 383, 1, 380, 520),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 765, 523, 380, 520),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 1147, 1045, 380, 520),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 765, 1, 380, 520),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 1147, 523, 380, 520),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 1529, 1045, 380, 520),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 1529, 524, 380, 519),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 1529, 4, 380, 518),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 1911, 528, 380, 517),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 1911, 13, 380, 513),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 2293, 1049, 380, 516),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 1911, 1047, 380, 518),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 1147, 2, 380, 519),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 1, 523, 380, 520),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 383, 1045, 380, 520),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 1, 1, 380, 520),
                new TextureRegion((Texture)manager.get("девочка идет.png"), 383, 523, 380, 520)
        };
    }

    public void unpackGirlHit() {
        girlHit = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 2456, 1023, 345, 511),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 3506, 1544, 349, 503),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 2447, 1, 351, 505),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 2800, 3, 349, 506),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 3149, 515, 349, 509),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 1752, 1534, 348, 513),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 693, 486, 346, 518),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 1, 483, 345, 520),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 348, 483, 343, 520),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 700, 1009, 343, 518),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 1041, 490, 344, 517),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 1405, 1533, 345, 514),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 2102, 1534, 346, 513),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 2447, 508, 347, 511),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 3156, 1538, 348, 509),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 3151, 4, 348, 509),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 3501, 4, 347, 509),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 3506, 1033, 347, 509),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 3500, 515, 348, 509),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 3155, 1026, 349, 509),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 2803, 1026, 350, 509),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 2796, 511, 351, 510),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 2803, 1537, 351, 510),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 2103, 1021, 351, 511),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 2450, 1536, 351, 511),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 2094, 504, 351, 512),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 1740, 500, 352, 513),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 1750, 1018, 351, 513),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 1397, 1015, 351, 514),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 1387, 495, 351, 515),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 1045, 1012, 350, 516),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 1053, 1531, 350, 516),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 702, 1530, 349, 517),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 351, 1529, 349, 518),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 350, 1006, 348, 519),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 1, 1527, 348, 520),
                new TextureRegion((Texture)manager.get("девочка бьет молотком.png"), 1, 1005, 347, 520)
        };
    }

    public void unpackGirlTakesItem() {
        girlTakesItem = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 1923, 527, 380, 522),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 1155, 526, 382, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 1539, 1051, 382, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 1, 1, 383, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 1154, 1, 382, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 386, 526, 383, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 1539, 526, 382, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 1923, 1051, 382, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 1538, 1, 382, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 1, 1051, 383, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 1, 526, 383, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 771, 1051, 382, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 386, 1, 382, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 386, 1051, 383, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 771, 526, 382, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 1155, 1051, 382, 523),
                new TextureRegion((Texture)manager.get("девочка берет предмет.png"), 770, 1, 382, 523)
        };
    }

    public void unpackItems() {
        items = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("items.png"), 1, 281, 661, 736), //Дверь открытая
                new TextureRegion((Texture)manager.get("items.png"), 664, 356, 651, 661), //Дверь закрытая
                new TextureRegion((Texture)manager.get("items.png"), 1317, 617, 400, 400), //Трещина на стенде
                new TextureRegion((Texture)manager.get("items.png"), 1719, 730, 328, 287), //Цветы
                new TextureRegion((Texture)manager.get("items.png"), 1, 1, 156, 278), //Мусорка
                new TextureRegion((Texture)manager.get("items.png"), 1317, 478, 74, 137), //Маска гнева
                new TextureRegion((Texture)manager.get("items.png"), 159, 139, 66, 140), //Молоток
                new TextureRegion((Texture)manager.get("items.png"), 664, 293, 53, 61), //Фото Кости
                new TextureRegion((Texture)manager.get("glass_broke.png"))
        };
    }

    public void unpackInterface() {
        interfaceElements = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("interface.png"), 1, 1, 104, 122), //дневник
                new TextureRegion((Texture)manager.get("interface.png"), 107, 1, 104, 122), //дневник2
                new TextureRegion((Texture)manager.get("interface.png"), 329, 19, 105, 104), //сумка
                new TextureRegion((Texture)manager.get("interface.png"), 213, 17, 114, 106), //сумка2
                new TextureRegion((Texture)manager.get("interface.png"), 436, 22, 104, 101), //фото
                new TextureRegion((Texture)manager.get("interface.png"), 542, 22, 104, 101), //фото2
                new TextureRegion((Texture)manager.get("interface.png"), 648, 22, 103, 101), //маска
                new TextureRegion((Texture)manager.get("interface.png"), 753, 22, 103, 101), //маска2
                new TextureRegion((Texture)manager.get("interface.png"), 858, 68, 37, 55), //курсор
                new TextureRegion((Texture)manager.get("interface.png"), 897, 84, 40, 39) //курсор2
        };
    }

    public void unpackDialog() {
        dialogElements = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("dialogs.png"), 1, 145, 1865, 326), //диалоговое окно
                new TextureRegion((Texture)manager.get("dialogs.png"), 1, 60, 413, 83), // длинный ответ при наведении
                new TextureRegion((Texture)manager.get("dialogs.png"), 1, 1, 412, 57), // длинный ответ
                new TextureRegion((Texture)manager.get("dialogs.png"), 416, 59, 274, 84), // коротокий ответ при наведении
                new TextureRegion((Texture)manager.get("dialogs.png"), 692, 86, 276, 57) // коротокий ответ
        };
    }

    public void unpackExitConfirm() {
        exitConfirm = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("confirmExit.png"), 1, 54, 760, 440), //окно с рамкой
                new TextureRegion((Texture)manager.get("confirmExit.png"), 763, 359, 361, 135), //текст
                new TextureRegion((Texture)manager.get("confirmExit.png"), 1, 1, 180, 51), //кнопка слева
                new TextureRegion((Texture)manager.get("confirmExit.png"), 763, 306, 180, 51), // светлая кнопка слева
                new TextureRegion((Texture)manager.get("confirmExit.png"), 1126, 443, 180, 51), // кнопка справа
                new TextureRegion((Texture)manager.get("confirmExit.png"), 183, 1, 180, 51) // светлая кнопка справа
        };
    }

    public void unpackMasks() {
        playerMask = new TextureRegion[2];
        playerMask[0] = new TextureRegion((Texture)manager.get("mask/печаль.png"));
        playerMask[1] = new TextureRegion((Texture)manager.get("mask/гнев.png"));

        maskSelector = new TextureRegion[] {
            new TextureRegion((Texture)manager.get("mask/селектор.png"))
        };

        maskSelectorButtons = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("mask/селектор_печаль.png")),
                new TextureRegion((Texture)manager.get("mask/селектор_гнев.png"))
        };

        maskSelectorIcons = new TextureRegion[] {
                new TextureRegion((Texture)manager.get("mask/selector_icons/селектор_печаль_icon.png")),
                new TextureRegion((Texture)manager.get("mask/selector_icons/селектор_гнев_icon.png")),
        };
    }

    private void unpackAll() {
        unpackBackground();
        unpackGirlStand();
        unpackGirlWalk();
        unpackGirlHit();
        unpackGirlTakesItem();
        unpackItems();
        unpackDiary();
        unpackInterface();
        unpackDialog();
        unpackDiaryPast();
        unpackExitConfirm();
        unpackInventory();
        unpackMasks();
        ResourcesClass.addResources(background, background, girlStand, girlWalk, items, girlHit, girlTakesItem,
                diary, diaryText0, interfaceElements, dialogElements, diaryText1, diaryText2, diaryText3,
                diaryText4, diaryText5, diaryText6, exitConfirm, inventory, inventoryIcons, backgroundAdditional, playerMask, maskSelector, maskSelectorButtons,
                maskSelectorIcons);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(manager.update()) {
            unpackAll();
            game.setScreen(new CorridorScene(game, this));
        }
        alfa += delta*3;
        loadIcon2Position.add((float) Math.cos(alfa), (float) Math.sin(alfa));
        MyGdxGame.batch.draw(loadIcon1, loadIcon2Position.x, loadIcon2Position.y, loadIcon2Width, loadIcon2Height);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        loadIcon1.dispose();
        loadIcon2.dispose();
        manager.dispose();
    }
}
