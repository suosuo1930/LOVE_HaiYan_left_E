# Generated by Django 2.1.3 on 2019-06-28 05:10

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('repository', '0003_auto_20190628_1037'),
    ]

    operations = [
        migrations.AlterModelOptions(
            name='userprofile',
            options={'permissions': (('repository_backend_page', '可访问所有 app 下的 所有表'), ('repository_app_page', '可访问一个 app 下的所有表'), ('repository_table_obj_list', '可访问一张表中的 所有 数据记录'), ('repository_table_obj_add', '可添加表记录数据'), ('repository_table_obj_change', '可对表记录数据进行修改'), ('repository_table_obj_delete', '可删除表记录数据')), 'verbose_name_plural': '自定义用户登录表'},
        ),
    ]