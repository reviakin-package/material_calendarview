package com.leusapp.material_calendar

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.calculate_pay.unit.OneDayDate

open class CalendarAdapter : BaseAdapter, View.OnTouchListener, CalendarAdapterBase {

    interface OnCalendarItemListener {
        fun onItemClick(day: OneDayDate)
    }

    var ctx: Context
    var inflater: LayoutInflater
    var days: Array<OneDayDate>
    private var item: Int = 0
    private var itemPressed: Int = 0
    var calendarItemListener: OnCalendarItemListener

    constructor(ctx: Context, days: Array<OneDayDate>, item: Int, itemPressed: Int) : super() {
        this.ctx = ctx
        this.days = days
        this.item = item
        this.itemPressed = itemPressed
        inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        calendarItemListener = ctx as OnCalendarItemListener
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView as TextView?
        var day: OneDayDate = getDay(position)
        if (view == null) {
            view = inflater.inflate(R.layout.material_day, parent, false) as TextView
        }


        if (view != null) {
            view.text = day.day.toString()
            view.tag = position
            view.setOnTouchListener(this)
            view.setBackgroundResource(item)
        }

        checkExistAndSwap(view, day)

        return view
    }



    override fun getItem(position: Int): Any {
        return days.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return days.size
    }

    private fun getDay(position: Int): OneDayDate {
        return days.get(position)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if(v != null && event != null){
            when(event.action){
                MotionEvent.ACTION_UP -> {
                    var temp: OneDayDate = getDay(v.getTag() as Int)
                    calendarItemListener.onItemClick(temp)
                }
            }
        }
        return false
    }

    override fun checkExistAndSwap(v: View, day: OneDayDate) {
        //...
    }

}