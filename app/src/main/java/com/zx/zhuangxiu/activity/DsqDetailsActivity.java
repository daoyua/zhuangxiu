package com.zx.zhuangxiu.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.ImageAdapter;
import com.zx.zhuangxiu.model.DaShiQuanLieBiaoTwo;
import com.zx.zhuangxiu.model.ToutiaoxiangqingBean;
import com.zx.zhuangxiu.view.MylistView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DsqDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView dsq_back, html;
    private TextView dsq_title, dsq_xiaotitle, dsq_content;

    private List<DaShiQuanLieBiaoTwo> daShiQuanLieBiaoTwoList = new ArrayList<>();
    private int pkId;
    String string = "<p style=\"text-align: justify;\">1、一般来说，一套两居室住宅内插座数量不少于15个，开关不少于8个。同一房间的开关高度应该一致，一般开关距离地面1.4米、插座距离地面0.3米为宜。</p><p style=\"text-align: justify;\">2、不能使用冬季没有库房或在室外散堆、被雨雪覆盖的木材、板材，及其它与木质有关的合成板材等，因为它的干湿度不易检测和观察，这些材料大多数都是表面干燥但内面潮湿，春风一吹极容易变形。</p><p style=\"text-align: justify;\">3、卫生间大多采用低彩度、高明度的色彩组合来衬托干净爽快的气氛，色彩运用上以卫浴设施为主色调，墙地色彩保持一致，这样才使整个卫生间有种和谐统一感。</p><p style=\"text-align: justify;\">4、厨房装修时尽量选用冷色调，而且要用偏浅色类。由于厨房相对其他房间会更热，因此，用暖色调会让人感觉室温高出两三度。</p><p style=\"text-align: justify;\">5、放洗衣机的阳台上做个小柜子，方便放一些杂物，如洗衣粉之内的杂物。这样既美观又实用。</p><p style=\"text-align: justify;\">6、吊顶建议用有微孔的铝扣板，以加强通风和预防冷凝水。若做石膏板吊顶，应先刷防水腻子，再刷防水涂料。PVC吊顶易产生冷凝水，并下滴，要慎用。</p><p style=\"text-align: justify;\">7、PPR管不仅适用于冷水管道，也适用于热水管道，甚至纯净饮用水管道。PPR管的接口采用热熔技术，管子之间完全融合到了一起，所以一旦安装打压测试通过，不会漏水。而且PPR管不会结垢。</p><p style=\"text-align: justify;\">8、卫生间淋浴器的高度在205-210厘米之间，盥洗盆高度（上沿口）在70-74厘米之间为宜，站立空间宽度不得少于50厘米。</p><p style=\"text-align: justify;\">9、电路需暗埋入墙的最好采用“3分线管”，吊顶走线则可用“4分线管”。“线管”内“电线”不得有“接头”，“接头”要能在另一个“线盒”内找到，且“管内电线”不得超过“管径”的60百分号。</p><p style=\"text-align: justify;\">10、一般来说，装修后的异味是混合体，其中对人体有害的物质主要是甲醛和苯。甲醛是溶于水的，在屋里多放几盆水，再摆放几盆花草是最好的。对苯更好的办法就是通风。</p><p style=\"text-align: justify;\">11、根据自己的实际需要合理安排布线，以免浪费，但是有的地方就不能省，比方浴室镜旁，要经常用风筒和剃须刀；还有如果在鞋柜里面或下面有个插座也不错，可以用烘鞋器。</p><p style=\"text-align: justify;\">12、洗手间的淋浴房一定要尺寸够大，而且要用透明玻璃，这样在里面冲凉不会显得狭促。</p><p style=\"text-align: justify;\">13、无论是墙砖还是地砖，都应该先将材料从室外搬到室内过渡24小时，适应了室内温度以后才能铺贴，以免施工后出现空鼓、脱落的现象。</p><p style=\"text-align: justify;\">14、餐厅的色彩配搭一般都是随着客厅的，这主要是从空间感的角度来考虑的，因为目前国内多数的住房设计中，餐厅和客厅都是相通的。对于餐厅单置的构造，色彩的使用上，宜采用暖色系，因为从色彩心理学上来讲，暖色有利于促进食欲。</p><p style=\"text-align: justify;\">15、厨房和卫生间内经常会有水和油烟，插座面板上最好安装防溅水盒或塑料挡板，这样能有效防止因油污、水汽侵入引起的短路</p><p style=\"text-align: justify;\">安装门锁要注意锁舌头上些腊，撞坏再上腊就晚了。如果没有门吸的话还要注意防止把手撞墙而损坏。</p><p style=\"text-align: justify;\">16、在没有设计师指导的情况下，家居配色的最佳方案是：墙浅，地中，家私深。</p><p style=\"text-align: justify;\">17、浴室的瓷砖一般来说，都会选择釉面砖。这种瓷砖表面涂有一层彩色的釉面，色彩比较丰富，而且比较容易清洁保养。对于浴室来说，除了舒适之外还必须美观整洁，这一点，釉面砖是较好的选择。</p><p style=\"text-align: justify;\">18、地面颜色要衬托家具的颜色，并且地面装修属于长久装修，一般情况下，不会经常更换，因此要选择时应考虑多方面的因素。其中，中性的颜色一直是主流颜色，但如果搭配得当，深色、浅色都可达到理想效果。</p><p style=\"text-align: justify;\">19、鞋柜的隔板不要做到头，留一点空间好让鞋子的灰能漏到最底层，水槽和燃气灶上方装灯。</p><p style=\"text-align: justify;\">20、主卧的色彩应以统一、和谐、淡雅为宜，对局部的原色搭配应慎重，稳重的色调较受欢迎，如绿色系活泼而富有朝气，粉红系欢快而柔美，蓝色系清凉浪漫等。</p><p style=\"text-align: justify;\">21、铺地砖是装修过程中最重要最必不可少的一个环节，出厂期超过三个月的水泥不能使用、墙地砖要浸水2小时候阴干才能铺贴。</p><p style=\"text-align: justify;\">22、坐厕类挑选：选购坐厕要注意冲水方式和耗水量。坐厕的冲水方式常见的有直冲式和虹吸式两种。一般来说，直冲式的坐厕冲水的噪音大些而且易返味。虹吸式坐厕属于静音坐厕，水封较高，不易返味。</p><p style=\"text-align: justify;\">23、抽油烟机和热水器的排气孔是要预先打好的，所以，在装修前一定要选好抽油烟机，炉具和热水器等，以确定打孔的大小。</p><p style=\"text-align: justify;\">24、门板封边需要用到封边条，它作用有两个，第一个是封边，防止潮气侵入。第二个是有效地杜绝板材的微量的甲醛释放。</p><p style=\"text-align: justify;\">25、进家门视线的第一落点是最该放装饰画的地方，这样你才不会觉得家里墙上很空，视线不好，同时还能产生新鲜感。</p><p style=\"text-align: justify;\">26、卧室装修要有一个主色调，且多用暖色调，色彩种类不要太多，温馨和谐，让人看着心情舒畅、有利于睡眠。</p><p style=\"text-align: justify;\">27、一些户主愿意把白色墙壁和深茶色地板搭配，这会使地板显得很暗。如果墙壁选择同为茶色系的开司米色，墙壁和地板的颜色就比较容易接近，空间也会显得大。</p>";

    private List<ToutiaoxiangqingBean.DataBean> mlist = new ArrayList<>();
    private ScrollView xqview;


    public static final String TEXT_TAG = "[#TEXT#]";//文字标识
    public static final String IMAGE_NET_TAG = "[#IMAGE_NET#]";//网络图片标识
    public static final String IMAGE_LOCAL_TAG = "[#IMAGE_LOCAL#]";//本地图片标识(assets目录下)
    public static final String SPLIT_TAG = "\\[\\#SPLIT\\#\\]";//分割处标识


    private String[] mData;//图文数据的列表
    Handler handler = new Handler();
    private ImageView dsq_image;
    private MylistView img_list;
    private List <String> list=new ArrayList<>();
    private ImageAdapter imageAdapter;
    private TextView dsq_xiao_detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsq_details);

        Bundle bundle = getIntent().getExtras();
        pkId = bundle.getInt("pkId");
        initView();//初始化ui
    }

    private void initView() {
        dsq_back = (TextView) findViewById(R.id.dsq_back);
        dsq_back.setOnClickListener(this);
        dsq_title = (TextView) findViewById(R.id.dsq_title);
        dsq_xiaotitle = (TextView) findViewById(R.id.dsq_xiaotitle);
        dsq_xiao_detail = (TextView) findViewById(R.id.dsq_xiao_detail);
        html = (TextView) findViewById(R.id.html);
        dsq_image = findViewById(R.id.dsq_tupian);
        xqview = findViewById(R.id.sv_main);
        img_list = findViewById(R.id.img_list);
        imageAdapter = new ImageAdapter(this,list);
        img_list.setAdapter(imageAdapter);


        getInfo();
    }

    private void loadWEB(final String s) {
        final Html.ImageGetter imgGetter = new Html.ImageGetter() {
            public Drawable getDrawable(String source) {
                Drawable drawable = null;
                URL url = null;
                try {
                    url = new URL(source);
                    drawable = Drawable.createFromStream(url.openStream(), "img");
                } catch (Exception e) {
                    return null;
                }
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
                        .getIntrinsicHeight());
                return drawable;
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
              final Spanned text = Html.fromHtml(s, imgGetter, null);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        html.setText(text);
                    }
                });
            }
        }).start();
    }

    private void getInfo() {
        String url = URLS.daShiQuanDetailsShow(pkId);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ToutiaoxiangqingBean>() {
            @Override
            public void onSuccess(final ToutiaoxiangqingBean response) {
                if (response.getResult() == 1) {

//                    loadWEB(response.getData().getImginfo());
                    dsq_xiaotitle.setText("" + response.getData().getTitle());
                    dsq_xiao_detail.setText("" + response.getData().getImginfo());
                    Picasso.with(DsqDetailsActivity.this).load(URLS.HTTP+response.getData().getImg()).error(R.mipmap.logo_zhanwei).into(dsq_image);
                    String detailUrl = response.getData().getDetailUrl();
                    String[] split = detailUrl.split(",");
                    list.addAll( Arrays.asList(split));
                    imageAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dsq_back:
                this.finish();
                break;
        }

    }


}
